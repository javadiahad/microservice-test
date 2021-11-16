import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { TransferService } from './transfer.service';
import * as path from 'path';
import { Matchers, Pact } from '@pact-foundation/pact';
import { TransferRequest } from '../models/TransferRequest';

describe('TransferServicePact', () => {

  const provider: Pact = new Pact({
    port: 80,
    log: path.resolve(process.cwd(), 'pact', 'logs', 'TransferServicePact.log'),
    dir: path.resolve(process.cwd(), '..', 'pacts'),
    spec: 3,
    logLevel: 'debug',
    consumer: 'product1-ui-client2',
    provider: 'transferservice'
  });

  // Setup Pact mock server for this service
  beforeAll(async () => {
    await provider.setup();
  });

  // Configure Angular Testbed for this service
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientModule
      ],
      providers: [
        TransferService
      ]
    });
  });

  // Verify mock service
  afterEach(async () => {
    await provider.verify();
  });

  // Create contract
  afterAll(async () => {
    await provider.finalize();
  });

  describe('transfer()', () => {

    const expectedTransferRequest: TransferRequest = {
      accountFrom: "A100",
      accountTo: "A200" ,
      amount: 20,
    };
    const transferId = 111;


    beforeAll(async () => {
      await provider.addInteraction({
        state: `Two Accounts with sufficient balance exist`,
        uponReceiving: 'a request to POST a transferRequest',
        withRequest: {
          method: 'POST',
          path: '/api/transfers',
          body: expectedTransferRequest,
          headers: {
            'Content-Type': 'application/json'
          }
        },
        willRespondWith: {
          status: 200,
          body: Matchers.somethingLike({
            id: transferId
          }),
          headers: {
            'Content-Type': 'application/json'
          }
        }
      });
    });

    it('should transfer requested amount', async () => {
      const accountService: TransferService = TestBed.get(TransferService);
      await accountService.create(expectedTransferRequest).toPromise().then(response => {
        expect(response).toEqual(transferId);
      });
    });

  });
});
