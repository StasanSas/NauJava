package ru.stas.NauJava.Service.ReportService.ResourceTreads;

public class ResourceCountingAmountUsers {
    private Long Amount;
    private Long time;

    public synchronized Long getAmount() { return Amount; }
    public synchronized void setAmount(Long Amount) { this.Amount = Amount;}

    public synchronized Long getTime() { return time; }
    public synchronized void setTime(Long time) { this.time = time; }
}
