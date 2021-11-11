import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { TransferService } from './transfer.service';
import * as path from 'path';
import { Matchers, Pact } from '@pact-foundation/pact';
import { TransferRequest } from '../models/TransferRequest';

describe('TransferServicePact', () => {

  const provider: Pact = new Pact({
    port: 1234,
    log: path.resolve(process.cwd(), 'pact', 'logs', 'mockserver-integration.log'),
    dir: path.resolve(process.cwd(), '..', 'pacts', 'transferService'),
    spec: 3,
    logLevel: 'debug',
    consumer: 'product1-ui',
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
        state: `provider proccess and transfer a new request`,
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
          status: 201,
          body: Matchers.somethingLike({
            id: transferId
          }),
          headers: {
            'Content-Type': 'application/json'
          }
        }
      });
    });

    it('should transfer requested ammount', async () => {
      const accountService: TransferService = TestBed.get(TransferService);
      await accountService.create(expectedTransferRequest).toPromise().then(response => {
        expect(response).toEqual(transferId);
      });
    });

  });
});
