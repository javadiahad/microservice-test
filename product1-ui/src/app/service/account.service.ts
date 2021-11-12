import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Account } from '../models/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private BASE_URL = 'api/accounts';

  constructor(private httpClient: HttpClient) {
  }


  open(resource: Account): Observable<String> {
    return this.httpClient
      .post(this.BASE_URL, resource).pipe(
        map(data => data['id']));
  }

  get(id: string): Observable<Account> {
    return this.httpClient.get<Account>(`${this.BASE_URL}/${id}`);
  }


  update(resource: Account, id: string): Observable<any> {
    return this.httpClient
      .put(`${this.BASE_URL}/${id}`, resource);
  }

  findAll(): Observable<Account[]> {
    return this.httpClient.get<Account[]>(this.BASE_URL)
      .pipe(map(res => res.map(element => new Account(element))));
  }


}

