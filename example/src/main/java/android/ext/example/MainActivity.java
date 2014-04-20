package android.ext.example;

import android.ext.adapter.AdapterExt;
import android.ext.app.ActivityExt;
import android.ext.app.FragmentExt;
import android.ext.collections.ArrayListExt;
import android.ext.widget.Toaster;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import static android.ext.core.Global.getSingleton;

public class MainActivity extends ActivityExt {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toaster.show(getSingleton(ExampleSingleton.class).getText());
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends FragmentExt {
        private AdapterExt mAdapter;
        private ArrayListExt mData;

        @Override
        public void onCreate(Bundle state) {
            super.onCreate(state);

            if (state == null) {
                mAdapter = new AdapterExt();
                mData = new ArrayListExt();
                mAdapter.setData(mData);

                mData.add("Hello");
                mData.add("World");
                mData.add("How are you?");
            } else {
                mAdapter = state.getParcelable("adapter");
                mData = (ArrayListExt) mAdapter.getData();
            }
        }

        @Override
        public void onSaveInstanceState(Bundle state) {
            super.onSaveInstanceState(state);

            state.putParcelable("adapter", mAdapter);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
            View result = inflater.inflate(R.layout.f_main, container, false);

            ListView list = (ListView) result.findViewById(android.R.id.list);
            list.setAdapter(mAdapter);

            return result;
        }

        @Override
        public void onResume() {
            super.onResume();

            Toaster.show(getContext().toString());
        }
    }
}
