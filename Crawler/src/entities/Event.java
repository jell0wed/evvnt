package entities;

import entities.base.MultipartPrice;
import entities.base.MultipartText;
import org.joda.time.DateTime;

/**
 * Created by jeremiep on 2017-02-21.
 */
public class Event {
    public MultipartText name;
    public MultipartText description;
    public String source_url;
    public DateTime start;
    public DateTime end;
    public MultipartPrice price;
    public Categories category;
}
