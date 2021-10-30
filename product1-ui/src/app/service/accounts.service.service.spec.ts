import { TestBed } from '@angular/core/testing';

import { Accounts.ServiceService } from './accounts.service.service';

describe('Accounts.ServiceService', () => {
  let service: Accounts.ServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Accounts.ServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
