
export class Account {
  id?: string;
  holderName: string;
  name: string;
  balance: number;


  constructor(obj?: any) {
    Object.assign(this, obj);
  }
}
