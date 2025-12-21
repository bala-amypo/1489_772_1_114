@RestController
@RequestMapping("/queue")
public class QueuePositionController {

    @Autowired
    private QueuePositionService service;

    @PostMapping
    public QueuePosition create(
            @RequestParam Long tokenId,
            @RequestParam Integer position) {

        return service.createQueue(tokenId, position);
    }
}
