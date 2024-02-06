import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Like } from '../models/Like';
import { LikeDTO } from '../models/LikeDTO';

@Injectable({
  providedIn: 'root'
})
export class LikeService {

  private BASE_URL = 'http://192.168.192.52:8080/api/like'
  private headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  saveLike(like: Like): Observable<any> {
    return this.http.post<any>(this.BASE_URL, like, {headers: this.headers})
  }

  saveLikeDTO(like: LikeDTO): Observable<any> {
    return this.http.post<any>(this.BASE_URL, like, {headers: this.headers})
  }

  saveLikeParams(userOneId: number, userTwoId:number) {
    return this.http.get<any>(this.BASE_URL + `/${userOneId}/${userTwoId}`)
  }

}
