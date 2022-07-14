package de.lohrfink.junit5.extension.example;

import lombok.RequiredArgsConstructor;

public class Kaffeevollautomat implements Kaffeevollautomat_LF2_using_ti_group_V10DT<Kaffeevollautomat.Model> {


    void execute(Model model) {
        new Kaffeevollautomat_LF2_using_ti_group_V10DTLogic().execute(this, model);
    }

    @Override
    public boolean isGetraenkAusgewaehlt(Model model) {
        return model.isGetraenkGewaehlt();
    }

    @Override
    public boolean isGewaehltesGetraenk(GewaehltesGetraenk arg0, Model model) {
        return arg0 == model.getraenk;
    }

    @Override
    public boolean isTasteZubereitungGedrueckt(Model model) {
        return model.tasteZubereitungGedrueckt;
    }

    @Override
    public boolean isTasteZuckerGedueckt(Model model) {
        return model.tasteZuckerGedrueckt;
    }

    @Override
    public boolean isTasteMilchGedrueckt(Model model) {
        return model.tasteMilchGedrueckt;
    }

    @Override
    public boolean isZubereitungAbgebrochen(Model model) {
        return model.zubereitungAbgebrochen;
    }

    @Override
    public boolean isZubereitungBeendet(Model model) {
        return model.zubereitungBeendet;
    }

    @Override
    public void doTasteZuckerAktivWaehlbar_0(Model model) {

    }

    @Override
    public void doTasteZuckerAktivWaehlbar_1(Model model) {

    }

    @Override
    public void doTasteMilchAktivWaehlbar_0(Model model) {

    }

    @Override
    public void doTasteMilchAktivWaehlbar_1(Model model) {

    }

    @Override
    public void doTasteZubereitungAktivWaehlbar_0(Model model) {

    }

    @Override
    public void doTasteZubereitungAktivWaehlbar_1(Model model) {

    }

    @Override
    public void doGetraenkZubereiten(Model model) {

    }

    @Override
    public void doZubereitungBeenden(Model model) {

    }

    @Override
    public void doTrace(String dtName, String version, int rules, int rule, Model model) {
        System.out.printf("%s[%s] - %s/%s%n", dtName, version, rule, rules);
    }

    @RequiredArgsConstructor
    static class Model {
        final GewaehltesGetraenk getraenk;
        final boolean tasteZubereitungGedrueckt;
        final boolean tasteZuckerGedrueckt;
        final boolean tasteMilchGedrueckt;

        final boolean zubereitungAbgebrochen;

        final boolean zubereitungBeendet;

        boolean isGetraenkGewaehlt() {
            return getraenk != null;
        }


    }

}
