/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { SuneofinanzasTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { MontosDxesoftDetailComponent } from '../../../../../../main/webapp/app/entities/montos/montos-dxesoft-detail.component';
import { MontosDxesoftService } from '../../../../../../main/webapp/app/entities/montos/montos-dxesoft.service';
import { MontosDxesoft } from '../../../../../../main/webapp/app/entities/montos/montos-dxesoft.model';

describe('Component Tests', () => {

    describe('MontosDxesoft Management Detail Component', () => {
        let comp: MontosDxesoftDetailComponent;
        let fixture: ComponentFixture<MontosDxesoftDetailComponent>;
        let service: MontosDxesoftService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuneofinanzasTestModule],
                declarations: [MontosDxesoftDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    MontosDxesoftService,
                    JhiEventManager
                ]
            }).overrideTemplate(MontosDxesoftDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MontosDxesoftDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MontosDxesoftService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new MontosDxesoft(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.montos).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
