import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/User';
import { Like } from '../models/Like';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private BASE_URL: string = 'http://192.168.192.52:8080/api/user'
  private LOGIN_URL: string = 'http://192.168.192.52:8080/api/user/login'
  private MATCH_URL: string = 'http://192.168.192.52:8080/api/user/match'
  private headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  login(user: User): Observable<any> {
    return this.http.post<any>(this.LOGIN_URL, user, {headers: this.headers})
  }

  getUsers(): Observable<any> {
    return this.http.get<any>(this.BASE_URL)
  }

  like(like: Like): Observable<any> {
    return this.http.post<any>(this.MATCH_URL, like, {headers: this.headers})
  }
}
