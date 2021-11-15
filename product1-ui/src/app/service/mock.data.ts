import { Account } from './account';

export const mockAccounts: Account[] = [
  {
    code: 'A100',
    holderName: 'Bill Gates',
    name: 'My cash in hand',      
    balance: 100000000.00,
  },
  {
    code: 'A200',
    holderName: 'Poor People',
    name: 'Living expenses',      
    balance: 0.00,
  },
];


export const expectedAccount: Account = {
  holderName: 'Bill Gates',
  name: 'My cash in hand',      
  balance: 0.00,
};

