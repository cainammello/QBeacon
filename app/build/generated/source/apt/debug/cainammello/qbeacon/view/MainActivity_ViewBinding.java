// Generated code from Butter Knife. Do not modify!
package cainammello.qbeacon.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cainammello.qbeacon.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MainActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.etDistance = Utils.findRequiredViewAsType(source, R.id.distance, "field 'etDistance'", EditText.class);
    target.tvResult = Utils.findRequiredViewAsType(source, R.id.result, "field 'tvResult'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etDistance = null;
    target.tvResult = null;

    this.target = null;
  }
}
