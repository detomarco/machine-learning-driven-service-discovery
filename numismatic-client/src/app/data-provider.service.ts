import {HttpClient, HttpResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataProviderService {

  constructor(
    private http: HttpClient
  ) {
  }

  private readonly baseUrl = '/api/numismatic';

  requestToken(body: string): Observable<{ access_token: string }> {
    const headers: { [key: string]: string } = {
      Authorization: 'Basic bnVtaXNtYXRpYy1jbGllbnQ6bnVtaXNtYXRpYy1zZWNyZXQ=',
      'Content-type': 'application/x-www-form-urlencoded;charset=utf-8'
    };

    return this.http.request<any>('POST', this.baseUrl + '/oauth/token', {headers, body});
  }

  request<B = any>(url: string, method: string, body?: any): Observable<B> {
    const headers: { [key: string]: string } = {
      Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    };

    return this.http.request<B>(method, `${this.baseUrl}${url}`, {body, headers});
  }

}

