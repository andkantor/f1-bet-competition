package andkantor.f1betting.model.setting;

import andkantor.f1betting.entity.Setting;
import andkantor.f1betting.repository.SettingRepository;

public class ConfigurationManager {

    private SettingRepository settingRepository;
    private Configuration configuration;

    public ConfigurationManager(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
        this.configuration = new Configuration(settingRepository.findAll());
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void save(Configuration configuration) {
        this.configuration = configuration;
        this.configuration.getSettings()
                .forEach((name, value) -> settingRepository.save(new Setting(name, value)));
    }

    public void save() {
        save(configuration);
    }

}
