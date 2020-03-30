import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CityService } from '../city.service';
@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.css']
})
export class CitiesComponent implements OnInit {

  constructor(private cityService: CityService, private changeDetectorRefs: ChangeDetectorRef) {}

  cityList: [{
    id: number;name: string;
  }];
  displayedColumns = ['id', 'name'];

  nextPgeNum: number;
  previousPgeNum: number;
  currentPgeNum: number;
  totalPages: number;

  isLastPage: boolean;
  isFirstPage: boolean;

  loading: boolean;
  error;

  ngOnInit(): void {
    this.currentPgeNum = 1;
    this.nextPgeNum = 2;
    this.previousPgeNum = null;

    this.getPageFromApi(this.currentPgeNum);
  }

  public goToNextPage() {
    if (!this.isLastPage) {
      this.getPageFromApi(this.currentPgeNum + 1);
      this.currentPgeNum += 1;
      this.nextPgeNum = this.isLastPage ? null : this.nextPgeNum + 1;
      this.previousPgeNum += 1

      this.changeDetectorRefs.detectChanges();
    }
  }

  public goToPreviousPage() {
    if (!this.isFirstPage) {
      this.getPageFromApi(this.currentPgeNum - 1);
      this.currentPgeNum -= 1;
      this.nextPgeNum = this.isFirstPage ? null : this.nextPgeNum - 1;
      this.previousPgeNum -= 1

      this.changeDetectorRefs.detectChanges();
    }
  }

  private getPageFromApi(page: number) {

    this.loading = true;
    this.cityService
      .getCities(page - 1)
      .subscribe(data => {
          this.loading = false;
          this.cityList = data.content;
          this.isFirstPage = data.first;
          this.isLastPage = data.last;
          this.totalPages = data.totalPages;
        },
        error => {
          this.loading = false;
          this.error = 'Error retrieving data.'
        }
      );
    this.changeDetectorRefs.detectChanges();

  }
}
