// Generated code from Butter Knife. Do not modify!
package cainammello.qbeacon.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import cainammello.qbeacon.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BeaconActivity_ViewBinding<T extends BeaconActivity> implements Unbinder {
  protected T target;

  @UiThread
  public BeaconActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.tvSala = Utils.findRequiredViewAsType(source, R.id.sala, "field 'tvSala'", TextView.class);
    target.tvBloco = Utils.findRequiredViewAsType(source, R.id.bloco, "field 'tvBloco'", TextView.class);
    target.tvDocente = Utils.findRequiredViewAsType(source, R.id.docente, "field 'tvDocente'", TextView.class);
    target.tvDisciplina = Utils.findRequiredViewAsType(source, R.id.disciplina, "field 'tvDisciplina'", TextView.class);
    target.tvInstituicao = Utils.findRequiredViewAsType(source, R.id.instituicao, "field 'tvInstituicao'", TextView.class);
    target.tvCampus = Utils.findRequiredViewAsType(source, R.id.campus, "field 'tvCampus'", TextView.class);
    target.tvAulaAnterior = Utils.findRequiredViewAsType(source, R.id.aulaAnterior, "field 'tvAulaAnterior'", TextView.class);
    target.tvAulaProxima = Utils.findRequiredViewAsType(source, R.id.aulaProxima, "field 'tvAulaProxima'", TextView.class);
    target.tvInicio = Utils.findRequiredViewAsType(source, R.id.inicio, "field 'tvInicio'", TextView.class);
    target.tvFim = Utils.findRequiredViewAsType(source, R.id.fim, "field 'tvFim'", TextView.class);
    target.vInfo = Utils.findRequiredViewAsType(source, R.id.view_info, "field 'vInfo'", LinearLayout.class);
    target.vProgress = Utils.findRequiredViewAsType(source, R.id.view_progress, "field 'vProgress'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvSala = null;
    target.tvBloco = null;
    target.tvDocente = null;
    target.tvDisciplina = null;
    target.tvInstituicao = null;
    target.tvCampus = null;
    target.tvAulaAnterior = null;
    target.tvAulaProxima = null;
    target.tvInicio = null;
    target.tvFim = null;
    target.vInfo = null;
    target.vProgress = null;

    this.target = null;
  }
}
