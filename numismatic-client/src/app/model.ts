
export interface City {
  name: string;
  lat: number;
  lng: number;
}

export interface User {
  username: string,
  email: string,
  name: string,
  surname: string,
  city: City;
}

export interface Users {
  users: User[];
}

export interface Countries {
  countries: string[];
}

export interface Coin {
  id: number;
  type: string;
  country: string;
  value: number;
  series: string;
  yearFrom: number;
  yearTo: number;
  diameter: number;
  thickness: number;
  weight: number;
  image: string;
}

export interface Coins {
  coins: Coin[];
}

export interface ServiceInstanceResponse {
  serviceInstances: ServiceInstance[]
}

export interface ServiceInstance {
  name: string;
  url: string;
  context: string;
  meanResponseTime: number;
}
