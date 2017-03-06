package andkantor.f1betting.model.setting;

import andkantor.f1betting.entity.Setting;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private Map<String, String> settings = new HashMap<>();

    public Configuration() {

    }

    public Configuration(Iterable<Setting> settings) {
        settings.forEach(setting -> this.settings.put(setting.getName(), setting.getValue()));
    }

    public Long getActiveSeason() {
        return Long.valueOf(get("active_season", "0"));
    }

    public void setActiveSeason(Long activeSeasonId) {
        settings.put("active_season", String.valueOf(activeSeasonId));
    }

    public int getNumberOfDriversToBetOn() {
        return Integer.valueOf(get("number_of_drivers_to_bet_on", "10"));
    }

    public void setNumberOfDriversToBetOn(int numberOfDriversToBetOn) {
        settings.put("number_of_drivers_to_bet_on", String.valueOf(numberOfDriversToBetOn));
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    private String get(String name, String defaultValue) {
        return settings.containsKey(name) ? settings.get(name) : defaultValue;
    }

}
