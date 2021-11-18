import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { TransferRequest } from '../models/TransferRequest';


@Injectable({
  providedIn: 'root'
})
export class TransferService {
  private BASE_URL = 'api/transfers';


  constructor(private httpClient: HttpClient) {
  }

  create(resource: TransferRequest): Observable<number> {
    return this.httpClient
      .post(this.BASE_URL, resource).pipe(
        map(data => data['id']));
  }
}
