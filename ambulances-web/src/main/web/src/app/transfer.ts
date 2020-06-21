import {Ambulance} from "./ambulance";
import {Journey} from "./journey";

export interface Transfer {
  id: string;
  ambulance: Ambulance;
  journeys: Array<Journey>;
  freePlaces: number;
}
