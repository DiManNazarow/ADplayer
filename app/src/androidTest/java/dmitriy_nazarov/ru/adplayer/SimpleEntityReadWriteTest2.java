package dmitriy_nazarov.ru.adplayer;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Assert.*;

import dmitriy_nazarov.ru.adplayer.features.db.AppDatabase;
import dmitriy_nazarov.ru.adplayer.features.tracklist.TrackListDao;
import dmitriy_nazarov.ru.adplayer.features.tracklist.TrackListRepository;
import dmitriy_nazarov.ru.adplayer.features.tracklist.models.Track;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class SimpleEntityReadWriteTest2 {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private AppDatabase appDatabase;
    private TrackListDao trackListDao;

    @Before
    public void createDB() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build();
        trackListDao = appDatabase.trackListDao();
    }

    @After
    public void tearDown(){
        appDatabase.close();
    }

    @Test
    public void test(){
        Track track = new Track(1L, "1", "one");
        Track track1 = new Track(2L,"2", "two");
        trackListDao.insertTrack(track);
        trackListDao.insertTrack(track1);
        try {
            List<Track> tr = getValue(trackListDao.getAllTracks());
            assertEquals(tr.get(0), track);
            assertEquals(tr.get(1), track1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static <T> T getValue(final LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        //noinspection unchecked
        return (T) data[0];
    }

}
