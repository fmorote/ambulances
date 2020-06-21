import {Transfer} from "./transfer";


export const TRANSFERS: Transfer[] = [
  {
    id: "1111111111",
    ambulance: {
      id: "A",
      seats: 6
    },
    journeys: [
      {
        id: "1",
        people: 4
      }
    ],
    freePlaces: 2
  },
  {
    id: "222222222",
    ambulance: {
      id: "B",
      seats: 8
    },
    journeys: [
      {
        id: "2",
        people: 4
      },
      {
        id: "3",
        people: 4
      }
    ],
    freePlaces: 0
  }
];
