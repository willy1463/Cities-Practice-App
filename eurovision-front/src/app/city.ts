export interface CityPage {
  content: [{
      id: number,
      name: string
    }],
    pageable: {},
    totalPages: number,
    totalElements: number,
    last: boolean,
    size: number,
    sort: {},
    numberOfElements: number,
    first: boolean,
    empty: boolean

}
