import { Component, OnInit } from '@angular/core';
import { CityService } from '../city.service';

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.css']
})
export class CitiesComponent implements OnInit {

  constructor( private cityService:  CityService) { }

  cityList: [{ id: number; name: string; }];
  displayedColumns = ['id', 'name'];

  loading: boolean;
  error;
    
  ngOnInit(): void {

    this.loading = true;
    this.cityService
      .getCities()
      .subscribe(data => {
        this.loading = false;
        this.cityList = data.content;
      },
      error => {
        this.loading = false;
        this.error = 'Error retrieving data.'
      }
    );
  }
}
