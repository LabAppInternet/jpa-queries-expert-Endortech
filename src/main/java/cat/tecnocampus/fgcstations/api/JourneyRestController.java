package cat.tecnocampus.fgcstations.api;

import cat.tecnocampus.fgcstations.application.DTOs.JourneyDTO;
import cat.tecnocampus.fgcstations.application.FgcJourneyService;
import cat.tecnocampus.fgcstations.domain.Journey;
import cat.tecnocampus.fgcstations.domain.JourneyId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/journeys")
public class JourneyRestController {
    private final FgcJourneyService fgcJourneyService;

    public JourneyRestController(FgcJourneyService fgcJouneyService) {
        this.fgcJourneyService = fgcJouneyService;
    }

    @GetMapping("/dto")
    public List<JourneyDTO> getAllJourneys() {
        return fgcJourneyService.getAllJourneysDTO();
    }

    @GetMapping("/domain")
    public List<Journey> getAllJourneysDomain() {
        return fgcJourneyService.getAllJourneysDomain();
    }

    @GetMapping("/origin/{origin}/destination/{destination}")
    public Journey getJourneyAcordingToOriginAndDestination(@PathVariable String origin, @PathVariable String destination) {
        return fgcJourneyService.getJourneyDomain(origin, destination);
    }

    @GetMapping("/id/origin/{origin}/destination/{destination}")
    public JourneyId getJourneyIdAcordingToOriginAndDestination(@PathVariable String origin, @PathVariable String destination) {
        return fgcJourneyService.getJourneyId(origin, destination);
    }
}
