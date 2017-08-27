package mx.com.dxesoft.suneofinanzas.service.impl;

import mx.com.dxesoft.suneofinanzas.datatypes.Alumno;
import mx.com.dxesoft.suneofinanzas.domain.Adeudo;
import mx.com.dxesoft.suneofinanzas.repository.AdeudosRepository;
import mx.com.dxesoft.suneofinanzas.service.AdeudosService;
import mx.com.dxesoft.suneofinanzas.service.dto.AdeudoDto;
import mx.com.dxesoft.util.BidxichiMoney;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * suneofinanzas, mx.com.dxesoft.suneofinanzas.service.impl . AdeudosServiceImpl
 * Created by ernesto on 20/08/17.
 */
@Service
@Transactional
public class AdeudosServiceImpl implements AdeudosService {

    private final Logger log = LoggerFactory.getLogger(AdeudosServiceImpl.class);

    private AdeudosRepository adeudosRepository;

    public AdeudosServiceImpl(AdeudosRepository adeudosRepository) {
        this.adeudosRepository = adeudosRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Adeudo> findAll(Pageable pageable) {
        log.debug("Request to get all Adeudo");
        return adeudosRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public AdeudoDto findAdeudosByAlumno(Alumno alumno) {
        Assert.notNull(alumno, "Alumno can not be null");
        log.debug("Request to find adeudos by alumno: ", alumno);

        List<Adeudo> adeudosAlumno = adeudosRepository.findByAlumnoCurpAndStatus(alumno.getCurp());

        BidxichiMoney saldoAlumno = new BidxichiMoney();

        for (Adeudo adeudo : adeudosAlumno) {
            saldoAlumno.add(adeudo.getMontos().getAmount());
        }

        return new AdeudoDto(alumno, adeudosAlumno, saldoAlumno);
    }
}
