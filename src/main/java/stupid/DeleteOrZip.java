package stupid;

import java.util.ArrayList;
import java.util.Set;

public class DeleteOrZip {
    private Set<Long> photosId;
    private ArrayList<Long> photosDelete = new ArrayList<>();

    public DeleteOrZip() {}

    public DeleteOrZip(Set<Long> photosId) {
        this.photosId = photosId;
    }

    public ArrayList<Long> getPhotosDelete() {
        return photosDelete;
    }

    public void setPhotosDelete(ArrayList<Long> photosDelete) {
        this.photosDelete = photosDelete;
    }

    public Set<Long> getPhotosId() {
        return photosId;
    }

    public void setPhotosId(Set<Long> photosId) {
        this.photosId = photosId;
    }
}
