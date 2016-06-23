package stupid;

import java.util.ArrayList;
import java.util.Set;

public class DeleteOrZip {
    private Set<Long> photosId;
    private ArrayList<Long> photosDelete = new ArrayList<>();
    private boolean delete;
    private boolean zip;

    public DeleteOrZip(Set<Long> photosId) {
        this.photosId = photosId;
    }

    public ArrayList<Long> getPhotosDelete() {
        return photosDelete;
    }

    public void setPhotosDelete(ArrayList<Long> photosDelete) {
        this.photosDelete = photosDelete;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isZip() {
        return zip;
    }

    public void setZip(boolean zip) {
        this.zip = zip;
    }

    public Set<Long> getPhotosId() {
        return photosId;
    }

    public void setPhotosId(Set<Long> photosId) {
        this.photosId = photosId;
    }
}
