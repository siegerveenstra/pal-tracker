package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private long lastkey = 0;
    private Map<Long, TimeEntry> entries = new HashMap<Long, TimeEntry>();

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        lastkey++;
        long key = lastkey;
        timeEntry.setId(key);
        entries.put(key, timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return entries.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(entries.values());
    }

    @Override
    public TimeEntry update(long key, TimeEntry timeEntry) {
        if (entries.get(key) == null) {
            return null;
        }
        entries.put(key, timeEntry);
        timeEntry.setId(key);
        return timeEntry;
    }

    @Override
    public void delete(long timeEntryId) {
        entries.remove(timeEntryId);
    }
}
