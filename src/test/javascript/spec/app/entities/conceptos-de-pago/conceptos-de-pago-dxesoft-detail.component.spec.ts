/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SuneofinanzasTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ConceptosDePagoDxesoftDetailComponent } from '../../../../../../main/webapp/app/entities/conceptos-de-pago/conceptos-de-pago-dxesoft-detail.component';
import { ConceptosDePagoDxesoftService } from '../../../../../../main/webapp/app/entities/conceptos-de-pago/conceptos-de-pago-dxesoft.service';
import { ConceptosDePagoDxesoft } from '../../../../../../main/webapp/app/entities/conceptos-de-pago/conceptos-de-pago-dxesoft.model';

describe('Component Tests', () => {

    describe('ConceptosDePagoDxesoft Management Detail Component', () => {
        let comp: ConceptosDePagoDxesoftDetailComponent;
        let fixture: ComponentFixture<ConceptosDePagoDxesoftDetailComponent>;
        let service: ConceptosDePagoDxesoftService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuneofinanzasTestModule],
                declarations: [ConceptosDePagoDxesoftDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ConceptosDePagoDxesoftService,
                    JhiEventManager
                ]
            }).overrideTemplate(ConceptosDePagoDxesoftDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConceptosDePagoDxesoftDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConceptosDePagoDxesoftService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ConceptosDePagoDxesoft(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.conceptosDePago).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
