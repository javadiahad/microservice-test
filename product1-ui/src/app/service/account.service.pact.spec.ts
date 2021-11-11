import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { AccountService } from './account.service';
import * as path from 'path';
import { Matchers, Pact } from '@pact-foundation/pact';
import { Account } from '../models/account';

describe('AccountServicePact', () => {

  const provider: Pact = new Pact({
    port: 1234,
    log: path.resolve(process.cwd(), 'pact', 'logs', 'mockserver-integration.log'),
    dir: path.resolve(process.cwd(), '..', 'pacts'),
    spec: 3,
    logLevel: 'debug',
    consumer: 'product1-ui-consume',
    provider: 'accountService'
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
        AccountService
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

  describe('get()', () => {

    const accountId = 'A100';

    const expectedAccount: Account = {
      holderName: 'Bill Gates',
      name: 'My cash in hand',      
      balance: 0.00,
    };

    beforeAll(async () => {
      await provider.addInteraction({
        state: `account A100 exists`,
        uponReceiving: 'a request to GET a account',
        withRequest: {
          method: 'GET',
          path: `/api/accounts/${accountId}`
        },
        willRespondWith: {
          status: 200,
          body: Matchers.somethingLike(expectedAccount)
        }
      });
    });

    it('should get a account', async () => {
      const accountService: AccountService = TestBed.get(AccountService);

      await accountService.get(accountId).toPromise().then(response => {
        expect(response).toEqual(expectedAccount);
      });
    });
  });

  describe('openAccount()', () => {

    const expectedAccount: Account = {
      holderName: 'Bill Gates',
      name: 'My cash in hand',      
      balance: 0.00,
    };

    const openedAccountId = "A100";

    beforeAll(async () => {
      await provider.addInteraction({
        state: `provider opens a new account`,
        uponReceiving: 'a request to POST a account',
        withRequest: {
          method: 'POST',
          path: '/api/accounts',
          body: expectedAccount,
          headers: {
            'Content-Type': 'application/json'
          }
        },
        willRespondWith: {
          status: 201,
          body: Matchers.somethingLike({
            id: openedAccountId
          }),
          headers: {
            'Content-Type': 'application/json'
          }
        }
      });
    });

    it('should open a account', async () => {
      const accountService: AccountService = TestBed.get(AccountService);
      await accountService.open(expectedAccount).toPromise().then(response => {
        expect(response).toEqual(openedAccountId);
      });
    });

  });

  describe('update()', () => {

    const expectedAccount: Account = {
      holderName: 'Bill Gates',
      name: 'My cash in hand',      
      balance: 0.00,
    };

    const accountId = 'A100';


    beforeAll(async () => {
      await provider.addInteraction({
        state: `account A100 exists`,
        uponReceiving: 'a request to PUT a account',
        withRequest: {
          method: 'PUT',
          path: `/api/accounts/${accountId}`,
          headers: {'Content-Type': 'application/json'},
          body: Matchers.somethingLike(expectedAccount)
        },
        willRespondWith: {
          status: 200,
          body: Matchers.somethingLike(expectedAccount)
        }
      });
    });

    it('should update a account', async () => {
      const accountService: AccountService = TestBed.get(AccountService);

      await accountService.update(expectedAccount, accountId).toPromise().then(response => {
        expect(response).toEqual(expectedAccount);
      });
    });
  });
});
