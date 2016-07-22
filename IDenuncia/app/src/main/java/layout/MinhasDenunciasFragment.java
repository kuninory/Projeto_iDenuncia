package layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.idenuncia.idenuncia.R;
import com.example.idenuncia.idenuncia.model.Denuncia;
import com.example.idenuncia.idenuncia.services.WebTaskDenuncia;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.NoSubscriberEvent;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class MinhasDenunciasFragment extends Fragment {

    private WebTaskDenuncia mDenunTask = null;
    private View mProgressView;
    private View mListView;
    ArrayList<Denuncia> denunciaList = new ArrayList<Denuncia>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_minhas_denuncias, container, false);

        mProgressView = rootView.findViewById(R.id.denun_progress);
        mListView = rootView.findViewById(R.id.listDenuncias);

        mDenunTask = new WebTaskDenuncia(getContext(), "0", "5", "0");
        mDenunTask.execute();

        return rootView;
    }

    private ArrayList<Denuncia> getListDenuncia() {

        return denunciaList;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mListView.setVisibility(show ? View.GONE : View.VISIBLE);
            mListView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mListView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mListView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Subscribe
    public void onEvent(ArrayList<Denuncia> denuncias) {
        mDenunTask = null;
        showProgress(false);

        denunciaList = denuncias;

        ArrayList<Denuncia> listContact = getListDenuncia();
        ListView lv = (ListView) getView().findViewById(R.id.listDenuncias);
        lv.setAdapter(new ItensAdapter(getActivity(), 0, denunciaList));

    }

    @Subscribe
    public void onEvent(Error error) {
        mDenunTask = null;
        showProgress(false);

        Snackbar snackbar = Snackbar
                .make(mListView, error.getMessage(), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Subscribe
    public void onAnything(NoSubscriberEvent randomEvent) {
        mDenunTask = null;
        showProgress(false);
    }
}