class Clock {

    int hours = 12;
    int minutes = 0;

    void next() {
        this.minutes++;

        if (this.minutes == 60){
            this.hours +=1;
            this.minutes = 0;
        }

        if (this.hours == 13){
            this.hours = 1;
        }
    }
}