package tr.edu.medipol.yazilimaraclari.AtaKirtasiye;
import java.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/urunlistele")
public class UrunListeleWebService {
	
	public record Urun( int urunSeriNo, String urunAdi, String urunMarka, double urunAdet, double urunFiyati, String urunSKT) {}

	
	private static final List<Urun> URUN_LISTESI = new ArrayList<>();
	static {
		URUN_LISTESI.add(new Urun(0001, "Makas", "AtaKirtasiye", 1, 40, ""));
		URUN_LISTESI.add(new Urun(0002, "Kil Heykel Hamuru", "DAS", 1, 75, "06/09/2025"));
		URUN_LISTESI.add(new Urun(0003, "A4 Kağıt 500'lu", "Harman Market", 1, 80, ""));

	}

@GetMapping("/")
public List<Urun> listele(){
    return URUN_LISTESI;
}

@GetMapping("/{no}")
public Urun bul(@PathVariable int no) {
    for (Urun urun : URUN_LISTESI) {
        if (urun.urunSeriNo() == no) {
            return urun;
        }
    }
    return null;
}

@DeleteMapping("/{no}")
public boolean sil(@PathVariable int no) {
    Urun urun = bul(no);
    if(urun != null) {
        URUN_LISTESI.remove(urun);
        return true;
    }
    return false;
}

@PostMapping("/")
public Urun ekle(@RequestBody Urun urun) {
    URUN_LISTESI.add(urun);
    return urun;
}
}
