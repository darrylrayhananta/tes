package World;

import Player.Player;
import main.Game;
import World.Environment.GameClock;
import World.Environment.Season;
import World.Environment.Weather;

public class Farm {
    private String farmName;
    private Player player;
    private FarmMap farmMap;
    private Season season;
    private Weather weather;
    private GameClock gameClock;
    private int day; // hari ke-n dalam satu musim
    private Point playerPosition;

    public Farm(String farmName, Player player) {
        this.farmName = farmName;
        this.player = player;
        this.farmMap = new FarmMap("Spakbor Hills");
        this.season = Season.getInstance();
        this.gameClock = GameClock.getInstance();
        this.weather = Weather.getInstance();
        this.day = 1;
        this.playerPosition = new Point(0, 0);  // Posisi awal pemain di (0, 0)
    }

    // --- Getter Methods ---
    public String getFarmName() { 
        return farmName; 
    }
    public Player getPlayer() { 
        return player; 
    }
    public FarmMap getFarmMap() { 
        return farmMap; 
    }
    public Season getSeason() { 
        return season; 
    }
    public GameClock getGameClock() { 
        return gameClock; 
    }
    public Weather getWeather() { 
        return weather; 
    }
    public int getDay() { 
        return day; 
    }
    public Point getPlayerPosition() {
        return playerPosition;
    }
    public void setPlayerPosition(Point position) {
        this.playerPosition = position;
    }

    // menggerakkan player
    public void movePlayer(int dx, int dy) {
        playerPosition.setX(playerPosition.getX() + dx);
        playerPosition.setY(playerPosition.getY() + dy);
    }

    // --- Day Progression ---
    public void nextDay() {
        day++;
        if (day > 10) {
            day = 1;
            season.nextSeason(); // ganti season setelah 10 hari
        }

        weather.nextWeather(season);
        gameClock.skipToMorning();
        farmMap.getTilesManagement().growCropsOneDay();

        System.out.println("Day " + day + " starts!");
        System.out.println("Season: " + season.getCurrentSeason() + ", Weather: " + weather.getCurrentWeather());
    }

    // --- Cheat Tools (manual override) ---
    public void cheatSetSeason(String seasonName) {
        season.setSeason(seasonName);
    }

    public void cheatSetWeather(String weatherName) {
        weather.setWeather(weatherName);
    }

    // --- Utility ---
    public void displayFarmMap() {
        farmMap.display();
    }

    public void displayTimeInfo() {
        System.out.println("Waktu: " + gameClock.getTime());
        System.out.println("Musim: " + season.getCurrentSeason());
        System.out.println("Hari ke-" + day);
        System.out.println("Cuaca: " + weather.getCurrentWeather());
    }

    public void advanceTime(int inGameMinutes) {
        gameClock.advance(inGameMinutes);
    }

    public boolean isRainy() {
        return weather.isRainy();
    }

    public void endDayAndSell() {
        System.out.println("Menjual barang dari shipping bin...");
        player.getInventory().addGold(ShippingLogic.sell(farmMap)); // logika harga bisa dari file eksternal
        nextDay();
    }
}