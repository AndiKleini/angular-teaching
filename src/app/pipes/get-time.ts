import { Pipe } from "@angular/core";

@Pipe({ name: 'getTime', standalone: true })
export class GetTimePipe {
    transform(v: Date): string {
        return v.toLocaleTimeString();
    }
}