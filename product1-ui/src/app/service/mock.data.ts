import { Account } from './account';

export const mockAccounts: Account[] = [
  {
    id: '1',
    holderName: 'Bill Gates',
    name: 'My cash in hand',      
    balance: 100.00,
  },
  {
    id: '2',
    holderName: 'Bill Gates',
    name: 'My saving account',      
    balance: 200.00,
  },
];


export const expectedAccount: Account = {
  holderName: 'Bill Gates',
  name: 'My cash in hand',      
  balance: 0.00,
};

