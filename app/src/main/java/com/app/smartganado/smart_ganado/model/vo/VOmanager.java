package com.app.smartganado.smart_ganado.model.vo;

import java.sql.Date;

public class VOmanager  {
    private static Backup backup=null;
    private Breed breed=null;
    private static Cattle cattle=null;
    private static CattleHistoryBook historyBook=null;
    private Estate estate=null;
    private Event event=null;
    private EventType eventType=null;
    private Gender gender=null;
    private HistoryType historyType=null;
    private static Lot lot=null;
    private ProductionBook productionBook=null;
    private Purpose purpose =null;
    private Rol rol=null;
    private Tank tank=null;
    private UserApp userApp=null;

    public static Backup getBackup(Date date, Integer phoneUser) {
        return backup==null ? new Backup(date,phoneUser) : backup;
    }


    public static Cattle getCattle() {
        return cattle==null ? new Cattle() : cattle;
    }

    public static CattleHistoryBook getCattleHistoryBook() {
        return historyBook==null ? new CattleHistoryBook() : historyBook;
    }

    public static Lot getLot() {
        return lot==null ? new Lot() : lot;

    }








}
