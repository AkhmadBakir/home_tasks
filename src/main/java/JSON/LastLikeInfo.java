package JSON;

class LastLikeInfo {
    // ваш код
    private String user;
    private int hours;
    private int minutes;

    public LastLikeInfo() {
    }

    public LastLikeInfo(String user, int hours, int minutes) {
        this.user = user;
        this.hours = hours;
        this.minutes = minutes;
    }

    public void setUser(String user) {
        this.user =user;
    }

    public String getUser() {
        return user;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public String toString() {
        return "LastLikeInfo{" +
                "user='" + user + '\'' +
                ", hours=" + hours +
                ", minutes=" + minutes +
                '}';
    }
}
