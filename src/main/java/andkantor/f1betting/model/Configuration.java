package andkantor.f1betting.model;

import andkantor.f1betting.entity.Setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configuration {

    private Map<String, String> settings = new HashMap<>();

    public Configuration() {
    }

    public Configuration(Iterable<Setting> settings) {
        settings.forEach((setting) -> this.settings.put(setting.getName(), setting.getValue()));
    }

    public int getNumberOfDriversToBetOn() {
        return Integer.valueOf(get("number_of_drivers_to_bet_on", "10"));
    }

    public void setNumberOfDriversToBetOn(int numberOfDriversToBetOn) {
        settings.put("number_of_drivers_to_bet_on", String.valueOf(numberOfDriversToBetOn));
    }

    public List<Setting> toSettingList() {
        List<Setting> result = new ArrayList<>();
        settings.forEach((name, value) -> result.add(new Setting(name, value)));
        return result;
    }

    private String get(String name, String defaultValue) {
        return settings.containsKey(name) ? settings.get(name) : defaultValue;
    }
}
