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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WatchId watchId = (WatchId) o;

            if (!watcher.equals(watchId.watcher)) return false;
            return watched.equals(watchId.watched);

        }

        @Override
        public int hashCode() {
            int result = watcher.hashCode();
            result = 31 * result + watched.hashCode();
            return result;
        }
    }
}
