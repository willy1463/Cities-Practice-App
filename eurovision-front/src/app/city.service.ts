import { Injectable } from '@angular/core';
import { CityPage } from './city';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class CityService {

  constructor(private httpClient: HttpClient) {
    console.log('City Service constructor')
  }

  public getCities(page: number) {
    return this.httpClient.get < CityPage > ('http://localhost:8080/api/cities/queryByPage?page=' + page)
  }
}
