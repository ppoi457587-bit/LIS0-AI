package com.lisoai.booster;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private final int bg = Color.rgb(11,16,22);
    private final int panel = Color.rgb(19,28,37);
    private final int lime = Color.rgb(178,255,89);
    private final int muted = Color.rgb(165,179,191);
    private LinearLayout root;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(bg);
        getWindow().setNavigationBarColor(bg);
        ScrollView scroll = new ScrollView(this);
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dp(18),dp(18),dp(18),dp(24));
        scroll.addView(root);
        setContentView(scroll);

        TextView logo = text("LISO AI", 28, lime, true);
        root.addView(logo);
        root.addView(text("PAINEL DE FLUIDEZ PARA ANDROID", 11, muted, true));
        addSpace(22);

        LinearLayout hero = card();
        hero.setGravity(Gravity.CENTER);
        hero.addView(text("✦", 48, lime, true));
        hero.addView(text("Deixe tudo mais fluido.", 23, Color.WHITE, true));
        TextView desc = text("Ajustes seguros e atalhos do sistema. Sem root.", 14, muted, false);
        desc.setGravity(Gravity.CENTER);
        hero.addView(desc);
        Button scan = button("ANALISAR MEU CELULAR");
        scan.setOnClickListener(v -> showDeviceInfo());
        hero.addView(scan);
        root.addView(hero);
        addSpace(14);

        root.addView(sectionTitle("AJUSTES DE DESEMPENHO"));
        root.addView(actionCard("Taxa de atualização", "Abra as opções de tela para escolher a maior taxa disponível.", "ABRIR TELA", () -> openSettings(Settings.ACTION_DISPLAY_SETTINGS)));
        root.addView(actionCard("Opções do desenvolvedor", "Permite ajustar escalas de animação para 0,5×. Você precisa ativar essas opções nas configurações do Android.", "ABRIR CONFIGURAÇÕES", () -> openSettings(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS)));
        root.addView(actionCard("Bateria e desempenho", "Confira o modo de desempenho do fabricante. Evite jogar enquanto o aparelho estiver superaquecido.", "ABRIR BATERIA", () -> openSettings(Settings.ACTION_BATTERY_SAVER_SETTINGS)));
        addSpace(8);
        TextView note = text("LIMITES REAIS\nEste app não altera silenciosamente outros aplicativos, não libera RAM magicamente e não dobra o FPS. O Android controla essas funções; algumas opções exigem ação manual.", 12, muted, false);
        note.setPadding(dp(4),dp(8),dp(4),dp(8));
        root.addView(note);
    }

    private void showDeviceInfo() {
        String info = "Modelo: " + android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL
            + "\nAndroid: " + android.os.Build.VERSION.RELEASE
            + "\n\nUse os atalhos abaixo para ajustar as opções disponíveis no seu aparelho.";
        new android.app.AlertDialog.Builder(this)
            .setTitle("Diagnóstico básico")
            .setMessage(info)
            .setPositiveButton("OK", null).show();
    }

    private LinearLayout actionCard(String title, String description, String label, Runnable action) {
        LinearLayout c = card();
        c.addView(text(title,18,Color.WHITE,true));
        TextView d = text(description,13,muted,false);
        d.setPadding(0,dp(6),0,dp(12));
        c.addView(d);
        Button b = button(label);
        b.setOnClickListener(v -> action.run());
        c.addView(b);
        root.addView(c);
        addSpace(10);
        return c;
    }

    private void openSettings(String action) {
        try { startActivity(new Intent(action)); }
        catch (Exception e) { startActivity(new Intent(Settings.ACTION_SETTINGS)); }
    }

    private LinearLayout card() {
        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);
        c.setPadding(dp(16),dp(16),dp(16),dp(16));
        c.setBackground(round(panel, dp(18), Color.rgb(41,56,69)));
        return c;
    }
    private TextView sectionTitle(String s) {
        TextView t = text(s,14,lime,true);
        t.setPadding(0,dp(6),0,dp(10));
        return t;
    }
    private TextView text(String s, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s); t.setTextSize(size); t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setLineSpacing(dp(3),1f);
        return t;
    }
    private Button button(String s) {
        Button b = new Button(this);
        b.setText(s); b.setTextSize(12); b.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        b.setTextColor(Color.rgb(15,22,10)); b.setAllCaps(false);
        b.setBackground(round(lime,dp(12),lime));
        b.setPadding(dp(10),dp(7),dp(10),dp(7));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.topMargin=dp(8); b.setLayoutParams(lp);
        return b;
    }
    private GradientDrawable round(int color, int radius, int stroke) {
        GradientDrawable d=new GradientDrawable(); d.setColor(color); d.setCornerRadius(radius); d.setStroke(dp(1),stroke); return d;
    }
    private void addSpace(int h) { View v=new View(this); root.addView(v,new LinearLayout.LayoutParams(1,dp(h))); }
    private int dp(float v) { return (int)(v*getResources().getDisplayMetrics().density+0.5f); }
}
