@Service
public class BankingService {

    @Autowired
    private ContoRepository contoRepository;
    
    @Autowired
    private MetadataRepository metadataRepository; // Il tuo vecchio amico per MongoDB

    @Transactional
    public void eseguiBonifico(Long daId, Long aId, BigDecimal importo) {
        Conto mittente = contoRepository.findById(daId).orElseThrow();
        Conto destinatario = contoRepository.findById(aId).orElseThrow();

        if (mittente.getSaldo().compareTo(importo) < 0) {
            throw new RuntimeException("Saldo insufficiente!");
        }

        mittente.setSaldo(mittente.getSaldo().subtract(importo));
        destinatario.setSaldo(destinatario.getSaldo().add(importo));

        contoRepository.save(mittente);
        contoRepository.save(destinatario);
        
        // Qui scatterà la logica IA asincrona che scriverà su MongoDB
    }
}