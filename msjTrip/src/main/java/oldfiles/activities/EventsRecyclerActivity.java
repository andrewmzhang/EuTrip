package oldfiles.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;
import com.msjBand.android.trip.R;
import oldfiles.fragments.EventsRecyclerFragment;
import oldfiles.services.UpdateEventsMasterService;


public class EventsRecyclerActivity extends AppCompatActivity  {

    public static final String TAG = "EventsRecyclerActivity";
    private static final String JOB_TAG = "MyServices";
    private static int hour = 3600;
    private Toolbar mToolbar;

    private GcmNetworkManager mGcmNetworkManager;


    private void constructJob() {


        PeriodicTask task = new PeriodicTask.Builder()
                .setService(UpdateEventsMasterService.class)
                .setPersisted(true)
                .setTag(JOB_TAG)
                .setPeriod((int) (hour * 2))
                .setFlex((int) (hour * .25))
                .setRequiredNetwork(Task.NETWORK_STATE_CONNECTED)
                .setRequiresCharging(false)
                .build();

        mGcmNetworkManager.schedule(task);

    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_recycler_list);

        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);



        mGcmNetworkManager = GcmNetworkManager.getInstance(this);
        constructJob();


        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            EventsRecyclerFragment fragment = new EventsRecyclerFragment();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
        }

    /*    navigationDrawer drawerFragment = (navigationDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout), );
*/
    }






}
