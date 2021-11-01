import { HttpClientModule } from '@angular/common/http';
import { TransferService } from './transfer.service';
import { TestBed } from '@angular/core/testing';
import { PactWeb, Interaction } from '@pact-foundation/pact-web';

describe('TransferService Pact', () => {
  let provider: PactWeb;
  let service: TransferService;

  beforeEach(async () => {
    provider = new PactWeb({
      consumer: 'product1-ui',
      provider: 'some-provider',
      port: 1234,
      cors: true
    });
    const interaction = new Interaction();
    await provider.addInteraction(interaction);

    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [TransferService]
    });
    
    service = TestBed.inject(TestService);
  });

  afterEach(async () => await provider.verify());
  afterAll(async () => await provider.finalize());
});
