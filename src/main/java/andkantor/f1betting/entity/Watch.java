package andkantor.f1betting.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "watch")
@IdClass(Watch.WatchId.class)
public class Watch {

    @Id
    @ManyToOne
    private User watcher;

    @Id
    @ManyToOne
    private User watched;

    public Watch() {
    }

    public Watch(User watcher, User watched) {
        this.watcher = watcher;
        this.watched = watched;
    }

    public User getWatcher() {
        return watcher;
    }

    public void setWatcher(User watcher) {
        this.watcher = watcher;
    }

    public User getWatched() {
        return watched;
    }

    public void setWatched(User watched) {
        this.watched = watched;
    }

    public static class WatchId implements Serializable {
        private String watcher;
        private String watched;
    }
}
