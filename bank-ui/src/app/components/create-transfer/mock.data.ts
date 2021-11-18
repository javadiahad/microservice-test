import { Account } from "src/app/models/account";
import { TransferRequest } from "src/app/models/transferRequest";

export const transferaccs: Account[] = [
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

export const expectedTransferRequest: TransferRequest = {
  accountFrom: "A100",
  accountTo: "A200" ,
  amount: 20,
};
