/*
 * Copyright 2006-2009, 2017, 2020 United States Government, as represented by the
 * Administrator of the National Aeronautics and Space Administration.
 * All rights reserved.
 * 
 * The NASA World Wind Java (WWJ) platform is licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * NASA World Wind Java (WWJ) also contains the following 3rd party Open Source
 * software:
 * 
 *     Jackson Parser – Licensed under Apache 2.0
 *     GDAL – Licensed under MIT
 *     JOGL – Licensed under  Berkeley Software Distribution (BSD)
 *     Gluegen – Licensed under Berkeley Software Distribution (BSD)
 * 
 * A complete listing of 3rd Party software notices and licenses included in
 * NASA World Wind Java (WWJ)  can be found in the WorldWindJava-v2.2 3rd-party
 * notices and licenses PDF found in code directory.
 */

package gov.nasa.worldwind.symbology.milstd2525;

import gov.nasa.worldwind.symbology.milstd2525.graphics.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Unit test for {@link MilStd2525GraphicFactory}. Tests that the factory supports all of the graphics that should be
 * supported. Update this list when new graphics are implemented.
 */
@RunWith(JUnit4.class)
public class MilStd2525TacticalGraphicFactoryTest
{
    @Test
    public void testGraphicSupported() throws IllegalAccessException
    {
        MilStd2525GraphicFactory factory = new MilStd2525GraphicFactory();
        assertTrue(factory.isSupported("GFGPGLP----AUSX"));
    }

    @Test
    public void testGraphicNotSupported() throws IllegalAccessException
    {
        MilStd2525GraphicFactory factory = new MilStd2525GraphicFactory();
        assertFalse(factory.isSupported("GFGPXXX----AUSX")); // Non-existent function ID.
    }

    @Test
    public void testTacGrp()
    {
        MilStd2525GraphicFactory factory = new MilStd2525GraphicFactory();

        for (String id : ALL_SUPPORTED_TACGRP)
        {
            StringBuilder sidc = new StringBuilder(id);

            for (char stdId : TacGrpSidcTest.ALL_STANDARD_IDENTITY)
            {
                for (char status : TacGrpSidcTest.ALL_STATUS)
                {
                    for (char echelon : TacGrpSidcTest.ALL_ECHELON)
                    {
                        sidc.setCharAt(1, stdId);
                        sidc.setCharAt(3, status);
                        sidc.setCharAt(11, echelon);

                        assertTrue("Missing graphic: " + sidc.toString(),
                            factory.isSupported(sidc.toString()));
                    }
                }
            }
        }
    }

    @Test
    public void testEms()
    {
        MilStd2525GraphicFactory factory = new MilStd2525GraphicFactory();

        for (String id : ALL_SUPPORTED_EMS)
        {
            StringBuilder sidc = new StringBuilder(id);

            for (char stdId : TacGrpSidcTest.ALL_STANDARD_IDENTITY)
            {
                for (char status : new char[] {'A', 'P'})
                {
                    sidc.setCharAt(1, stdId);
                    sidc.setCharAt(3, status);

                    assertTrue("Missing graphic: " + sidc.toString(), factory.isSupported(sidc.toString()));
                }
            }
        }
    }

    @Test
    public void testMetoc()
    {
        MilStd2525GraphicFactory factory = new MilStd2525GraphicFactory();

        for (String id : ALL_SUPPORTED_METOC)
        {
            assertTrue("Missing graphic: " + id, factory.isSupported(id));
        }
    }

    /**
     * All graphics from Appendix B that have been implemented. Update this list when new graphics are implemented. This
     * list should match the list of supported graphics at https://worldwind.arc.nasa.gov/java/tutorials/tactical-graphic-status/
     */
    private static final String[] ALL_SUPPORTED_TACGRP = {
        TacGrpSidc.TSK_DSTY,
        TacGrpSidc.TSK_ITDT,
        TacGrpSidc.TSK_NEUT,
        TacGrpSidc.C2GM_GNL_PNT_USW_UH2_DTM,
        TacGrpSidc.C2GM_GNL_PNT_USW_UH2_BCON,
        TacGrpSidc.C2GM_GNL_PNT_USW_UH2_LCON,
        TacGrpSidc.C2GM_GNL_PNT_USW_UH2_SNK,
        TacGrpSidc.C2GM_GNL_PNT_USW_SNBY,
        TacGrpSidc.C2GM_GNL_PNT_USW_SNBY_PTNCTR,
        TacGrpSidc.C2GM_GNL_PNT_USW_SNBY_DIFAR,
        TacGrpSidc.C2GM_GNL_PNT_USW_SNBY_LOFAR,
        TacGrpSidc.C2GM_GNL_PNT_USW_SNBY_CASS,
        TacGrpSidc.C2GM_GNL_PNT_USW_SNBY_DICASS,
        TacGrpSidc.C2GM_GNL_PNT_USW_SNBY_BT,
        TacGrpSidc.C2GM_GNL_PNT_USW_SNBY_ANM,
        TacGrpSidc.C2GM_GNL_PNT_USW_SNBY_VLAD,
        TacGrpSidc.C2GM_GNL_PNT_USW_SNBY_ATAC,
        TacGrpSidc.C2GM_GNL_PNT_USW_SNBY_RO,
        TacGrpSidc.C2GM_GNL_PNT_USW_SNBY_KGP,
        TacGrpSidc.C2GM_GNL_PNT_USW_SNBY_EXP,
        TacGrpSidc.C2GM_GNL_PNT_USW_SRH,
        TacGrpSidc.C2GM_GNL_PNT_USW_SRH_ARA,
        TacGrpSidc.C2GM_GNL_PNT_USW_SRH_DIPPSN,
        TacGrpSidc.C2GM_GNL_PNT_USW_SRH_CTR,
        TacGrpSidc.C2GM_GNL_PNT_REFPNT,
        TacGrpSidc.C2GM_GNL_PNT_REFPNT_NAVREF,
        TacGrpSidc.C2GM_GNL_PNT_REFPNT_SPLPNT,
        TacGrpSidc.C2GM_GNL_PNT_REFPNT_DLRP,
        TacGrpSidc.C2GM_GNL_PNT_REFPNT_PIM,
        TacGrpSidc.C2GM_GNL_PNT_REFPNT_MRSH,
        TacGrpSidc.C2GM_GNL_PNT_REFPNT_WAP,
        TacGrpSidc.C2GM_GNL_PNT_REFPNT_CRDRTB,
        TacGrpSidc.C2GM_GNL_PNT_REFPNT_PNTINR,
        TacGrpSidc.C2GM_GNL_PNT_WPN_AIMPNT,
        TacGrpSidc.C2GM_GNL_PNT_WPN_DRPPNT,
        TacGrpSidc.C2GM_GNL_PNT_WPN_ENTPNT,
        TacGrpSidc.C2GM_GNL_PNT_WPN_GRDZRO,
        TacGrpSidc.C2GM_GNL_PNT_WPN_MSLPNT,
        TacGrpSidc.C2GM_GNL_PNT_WPN_IMTPNT,
        TacGrpSidc.C2GM_GNL_PNT_WPN_PIPNT,
        TacGrpSidc.C2GM_GNL_PNT_FRMN,
        TacGrpSidc.C2GM_GNL_PNT_HBR,
        TacGrpSidc.C2GM_GNL_PNT_HBR_PNTQ,
        TacGrpSidc.C2GM_GNL_PNT_HBR_PNTA,
        TacGrpSidc.C2GM_GNL_PNT_HBR_PNTY,
        TacGrpSidc.C2GM_GNL_PNT_HBR_PNTX,
        TacGrpSidc.C2GM_GNL_PNT_RTE,
        TacGrpSidc.C2GM_GNL_PNT_RTE_RDV,
        TacGrpSidc.C2GM_GNL_PNT_RTE_DVSN,
        TacGrpSidc.C2GM_GNL_PNT_RTE_WAP,
        TacGrpSidc.C2GM_GNL_PNT_RTE_PIM,
        TacGrpSidc.C2GM_GNL_PNT_RTE_PNTR,
        TacGrpSidc.C2GM_GNL_PNT_ACTL,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_CAP,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_ABNEW,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_TAK,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_ASBWF,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_ASBWR,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_SUWF,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_SUWR,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_MIWF,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_MIWR,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_SKEIP,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_TCN,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_TMC,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_RSC,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_RPH,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_UA,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_VTUA,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_ORB,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_ORBF8,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_ORBRT,
        TacGrpSidc.C2GM_GNL_PNT_ACTL_ORBRD,
        TacGrpSidc.C2GM_GNL_PNT_ACTPNT,
        TacGrpSidc.C2GM_GNL_PNT_ACTPNT_CHKPNT,
        TacGrpSidc.C2GM_GNL_PNT_ACTPNT_CONPNT,
        TacGrpSidc.C2GM_GNL_PNT_ACTPNT_CRDPNT,
        TacGrpSidc.C2GM_GNL_PNT_ACTPNT_DCNPNT,
        TacGrpSidc.C2GM_GNL_PNT_ACTPNT_LNKUPT,
        TacGrpSidc.C2GM_GNL_PNT_ACTPNT_PSSPNT,
        TacGrpSidc.C2GM_GNL_PNT_ACTPNT_RAYPNT,
        TacGrpSidc.C2GM_GNL_PNT_ACTPNT_RELPNT,
        TacGrpSidc.C2GM_GNL_PNT_ACTPNT_STRPNT,
        TacGrpSidc.C2GM_GNL_PNT_ACTPNT_AMNPNT,
        TacGrpSidc.C2GM_GNL_PNT_ACTPNT_WAP,
        TacGrpSidc.C2GM_GNL_PNT_SCTL,
        TacGrpSidc.C2GM_GNL_PNT_SCTL_USV,
        TacGrpSidc.C2GM_GNL_PNT_SCTL_USV_RMV,
        TacGrpSidc.C2GM_GNL_PNT_SCTL_USV_ASW,
        TacGrpSidc.C2GM_GNL_PNT_SCTL_USV_SUW,
        TacGrpSidc.C2GM_GNL_PNT_SCTL_USV_MIW,
        TacGrpSidc.C2GM_GNL_PNT_SCTL_ASW,
        TacGrpSidc.C2GM_GNL_PNT_SCTL_SUW,
        TacGrpSidc.C2GM_GNL_PNT_SCTL_MIW,
        TacGrpSidc.C2GM_GNL_PNT_SCTL_PKT,
        TacGrpSidc.C2GM_GNL_PNT_SCTL_RDV,
        TacGrpSidc.C2GM_GNL_PNT_SCTL_RSC,
        TacGrpSidc.C2GM_GNL_PNT_SCTL_REP,
        TacGrpSidc.C2GM_GNL_PNT_SCTL_NCBTT,
        TacGrpSidc.C2GM_GNL_PNT_UCTL,
        TacGrpSidc.C2GM_GNL_PNT_UCTL_UUV,
        TacGrpSidc.C2GM_GNL_PNT_UCTL_UUV_ASW,
        TacGrpSidc.C2GM_GNL_PNT_UCTL_UUV_SUW,
        TacGrpSidc.C2GM_GNL_PNT_UCTL_UUV_MIW,
        TacGrpSidc.C2GM_GNL_PNT_UCTL_SBSTN,
        TacGrpSidc.C2GM_GNL_PNT_UCTL_SBSTN_ASW,
        TacGrpSidc.C2GM_GNL_LNE_BNDS,
        TacGrpSidc.C2GM_GNL_LNE_FLOT,
        TacGrpSidc.C2GM_GNL_LNE_LOC,
        TacGrpSidc.C2GM_GNL_LNE_PHELNE,
        TacGrpSidc.C2GM_GNL_LNE_LITLNE,
        TacGrpSidc.C2GM_GNL_ARS_GENARA,
        TacGrpSidc.C2GM_GNL_ARS_ABYARA,
        TacGrpSidc.C2GM_GNL_ARS_EMTARA,
        TacGrpSidc.C2GM_GNL_ARS_FTFDAR,
        TacGrpSidc.C2GM_GNL_ARS_DRPZ,
        TacGrpSidc.C2GM_GNL_ARS_EZ,
        TacGrpSidc.C2GM_GNL_ARS_LZ,
        TacGrpSidc.C2GM_GNL_ARS_PZ,
        TacGrpSidc.C2GM_GNL_ARS_SRHARA,
        TacGrpSidc.C2GM_GNL_ARS_LAARA,
        TacGrpSidc.C2GM_GNL_ARS_AIRFZ,
        TacGrpSidc.C2GM_AVN_PNT_ACP,
        TacGrpSidc.C2GM_AVN_PNT_COMMCP,
        TacGrpSidc.C2GM_AVN_PNT_PUP,
        TacGrpSidc.C2GM_AVN_PNT_DAPP,
        TacGrpSidc.C2GM_AVN_LNE_ACDR,
        TacGrpSidc.C2GM_AVN_LNE_MRR,
        TacGrpSidc.C2GM_AVN_LNE_SAAFR,
        TacGrpSidc.C2GM_AVN_LNE_UAR,
        TacGrpSidc.C2GM_AVN_LNE_LLTR,
        TacGrpSidc.C2GM_AVN_ARS_ROZ,
        TacGrpSidc.C2GM_AVN_ARS_SHRDEZ,
        TacGrpSidc.C2GM_AVN_ARS_HIDACZ,
        TacGrpSidc.C2GM_AVN_ARS_MEZ,
        TacGrpSidc.C2GM_AVN_ARS_MEZ_LAMEZ,
        TacGrpSidc.C2GM_AVN_ARS_MEZ_HAMEZ,
        TacGrpSidc.C2GM_AVN_ARS_WFZ,
        TacGrpSidc.C2GM_DCPN_DMY,
        TacGrpSidc.C2GM_DCPN_AAFF,
        TacGrpSidc.C2GM_DCPN_DAFF,
        TacGrpSidc.C2GM_DEF_PNT_TGTREF,
        TacGrpSidc.C2GM_DEF_PNT_OBSPST,
        TacGrpSidc.C2GM_DEF_PNT_OBSPST_CBTPST,
        TacGrpSidc.C2GM_DEF_PNT_OBSPST_RECON,
        TacGrpSidc.C2GM_DEF_PNT_OBSPST_FWDOP,
        TacGrpSidc.C2GM_DEF_PNT_OBSPST_SOP,
        TacGrpSidc.C2GM_DEF_PNT_OBSPST_CBRNOP,
        TacGrpSidc.C2GM_DEF_LNE_FEBA,
        TacGrpSidc.C2GM_DEF_LNE_PDF,
        TacGrpSidc.C2GM_DEF_ARS_BTLPSN,
        TacGrpSidc.C2GM_DEF_ARS_BTLPSN_PBNO,
        TacGrpSidc.C2GM_DEF_ARS_EMTARA,
        TacGrpSidc.C2GM_OFF_PNT_PNTD,
        TacGrpSidc.C2GM_OFF_LNE_AXSADV_AVN,
        TacGrpSidc.C2GM_OFF_LNE_AXSADV_ABN,
        TacGrpSidc.C2GM_OFF_LNE_AXSADV_ATK,
        TacGrpSidc.C2GM_OFF_LNE_AXSADV_GRD_MANATK,
        TacGrpSidc.C2GM_OFF_LNE_AXSADV_GRD_SUPATK,
        TacGrpSidc.C2GM_OFF_LNE_DIRATK_AVN,
        TacGrpSidc.C2GM_OFF_LNE_DIRATK_GRD_MANATK,
        TacGrpSidc.C2GM_OFF_LNE_DIRATK_GRD_SUPATK,
        TacGrpSidc.C2GM_OFF_LNE_FCL,
        TacGrpSidc.C2GM_OFF_LNE_INFNLE,
        TacGrpSidc.C2GM_OFF_LNE_LMTADV,
        TacGrpSidc.C2GM_OFF_LNE_LD,
        TacGrpSidc.C2GM_OFF_LNE_LDLC,
        TacGrpSidc.C2GM_OFF_LNE_PLD,
        TacGrpSidc.C2GM_OFF_ARS_ASTPSN,
        TacGrpSidc.C2GM_OFF_ARS_ATKPSN,
        TacGrpSidc.C2GM_OFF_ARS_AFP,
        TacGrpSidc.C2GM_OFF_ARS_SFP,
        TacGrpSidc.C2GM_OFF_ARS_OBJ,
        TacGrpSidc.C2GM_OFF_ARS_PBX,
        TacGrpSidc.C2GM_SPL_LNE_AMB,
        TacGrpSidc.C2GM_SPL_LNE_HGL,
        TacGrpSidc.C2GM_SPL_LNE_REL,
        TacGrpSidc.C2GM_SPL_LNE_BRGH,
        TacGrpSidc.C2GM_SPL_ARA_AOO,
        TacGrpSidc.C2GM_SPL_ARA_AHD,
        TacGrpSidc.C2GM_SPL_ARA_ENCMT,
        TacGrpSidc.C2GM_SPL_ARA_NAI,
        TacGrpSidc.C2GM_SPL_ARA_TAI,
        TacGrpSidc.MOBSU_OBST_ATO_TDTSM_FIXPFD,
        TacGrpSidc.MOBSU_OBST_ATO_TDTSM_MVB,
        TacGrpSidc.MOBSU_OBST_ATO_TDTSM_MVBPFD,
        TacGrpSidc.MOBSU_OBST_BBY,
        TacGrpSidc.MOBSU_OBST_MNE_USPMNE,
        TacGrpSidc.MOBSU_OBST_MNE_ATMNE,
        TacGrpSidc.MOBSU_OBST_MNE_ATMAHD,
        TacGrpSidc.MOBSU_OBST_MNE_ATMDIR,
        TacGrpSidc.MOBSU_OBST_MNE_APMNE,
        TacGrpSidc.MOBSU_OBST_MNE_WAMNE,
        TacGrpSidc.MOBSU_OBST_AVN_TWR_LOW,
        TacGrpSidc.MOBSU_OBST_AVN_TWR_HIGH,
        TacGrpSidc.MOBSU_OBSTBP_CSGSTE_ERP,
        TacGrpSidc.MOBSU_SU_ESTOF,
        TacGrpSidc.MOBSU_SU_FRT,
        TacGrpSidc.MOBSU_SU_SUFSHL,
        TacGrpSidc.MOBSU_SU_UGDSHL,
        TacGrpSidc.MOBSU_CBRN_MSDZ,
        TacGrpSidc.MOBSU_CBRN_NDGZ,
        TacGrpSidc.MOBSU_CBRN_FAOTP,
        TacGrpSidc.MOBSU_CBRN_RADA,
        TacGrpSidc.MOBSU_CBRN_BIOCA,
        TacGrpSidc.MOBSU_CBRN_CMLCA,
        TacGrpSidc.MOBSU_CBRN_REEVNT_BIO,
        TacGrpSidc.MOBSU_CBRN_REEVNT_CML,
        TacGrpSidc.MOBSU_CBRN_DECONP_USP,
        TacGrpSidc.MOBSU_CBRN_DECONP_ALTUSP,
        TacGrpSidc.MOBSU_CBRN_DECONP_TRP,
        TacGrpSidc.MOBSU_CBRN_DECONP_EQT,
        TacGrpSidc.MOBSU_CBRN_DECONP_EQTTRP,
        TacGrpSidc.MOBSU_CBRN_DECONP_OPDECN,
        TacGrpSidc.MOBSU_CBRN_DECONP_TRGH,
        TacGrpSidc.MOBSU_CBRN_DRCL,
        TacGrpSidc.FSUPP_PNT_TGT_PTGT,
        TacGrpSidc.FSUPP_PNT_TGT_NUCTGT,
        TacGrpSidc.FSUPP_PNT_C2PNT_FSS,
        TacGrpSidc.FSUPP_PNT_C2PNT_SCP,
        TacGrpSidc.FSUPP_PNT_C2PNT_FP,
        TacGrpSidc.FSUPP_PNT_C2PNT_RP,
        TacGrpSidc.FSUPP_PNT_C2PNT_HP,
        TacGrpSidc.FSUPP_PNT_C2PNT_LP,
        TacGrpSidc.FSUPP_LNE_LNRTGT,
        TacGrpSidc.FSUPP_LNE_LNRTGT_LSTGT,
        TacGrpSidc.FSUPP_LNE_LNRTGT_FPF,
        TacGrpSidc.FSUPP_LNE_C2LNE_FSCL,
        TacGrpSidc.FSUPP_LNE_C2LNE_CFL,
        TacGrpSidc.FSUPP_LNE_C2LNE_NFL,
        TacGrpSidc.FSUPP_LNE_C2LNE_RFL,
        TacGrpSidc.FSUPP_LNE_C2LNE_MFP,
        TacGrpSidc.FSUPP_ARS_ARATGT,
        TacGrpSidc.FSUPP_ARS_ARATGT_RTGTGT,
        TacGrpSidc.FSUPP_ARS_ARATGT_CIRTGT,
        TacGrpSidc.FSUPP_ARS_ARATGT_SGTGT,
        TacGrpSidc.FSUPP_ARS_ARATGT_SMK,
        TacGrpSidc.FSUPP_ARS_ARATGT_BMARA,
        TacGrpSidc.FSUPP_ARS_C2ARS_FSA_IRR,
        TacGrpSidc.FSUPP_ARS_C2ARS_FSA_RTG,
        TacGrpSidc.FSUPP_ARS_C2ARS_FSA_CIRCLR,
        TacGrpSidc.FSUPP_ARS_C2ARS_ACA_IRR,
        TacGrpSidc.FSUPP_ARS_C2ARS_ACA_RTG,
        TacGrpSidc.FSUPP_ARS_C2ARS_ACA_CIRCLR,
        TacGrpSidc.FSUPP_ARS_C2ARS_FFA_IRR,
        TacGrpSidc.FSUPP_ARS_C2ARS_FFA_RTG,
        TacGrpSidc.FSUPP_ARS_C2ARS_FFA_CIRCLR,
        TacGrpSidc.FSUPP_ARS_C2ARS_NFA_IRR,
        TacGrpSidc.FSUPP_ARS_C2ARS_NFA_RTG,
        TacGrpSidc.FSUPP_ARS_C2ARS_NFA_CIRCLR,
        TacGrpSidc.FSUPP_ARS_C2ARS_RFA_IRR,
        TacGrpSidc.FSUPP_ARS_C2ARS_RFA_RTG,
        TacGrpSidc.FSUPP_ARS_C2ARS_RFA_CIRCLR,
        TacGrpSidc.FSUPP_ARS_C2ARS_PAA_RTG,
        TacGrpSidc.FSUPP_ARS_C2ARS_PAA_CIRCLR,
        TacGrpSidc.FSUPP_ARS_C2ARS_SNSZ_IRR,
        TacGrpSidc.FSUPP_ARS_C2ARS_SNSZ_RTG,
        TacGrpSidc.FSUPP_ARS_C2ARS_SNSZ_CIRCLR,
        TacGrpSidc.FSUPP_ARS_C2ARS_DA_IRR,
        TacGrpSidc.FSUPP_ARS_C2ARS_DA_RTG,
        TacGrpSidc.FSUPP_ARS_C2ARS_DA_CIRCLR,
        TacGrpSidc.FSUPP_ARS_C2ARS_ZOR_IRR,
        TacGrpSidc.FSUPP_ARS_C2ARS_ZOR_RTG,
        TacGrpSidc.FSUPP_ARS_C2ARS_ZOR_CIRCLR,
        TacGrpSidc.FSUPP_ARS_C2ARS_TBA_IRR,
        TacGrpSidc.FSUPP_ARS_C2ARS_TBA_RTG,
        TacGrpSidc.FSUPP_ARS_C2ARS_TBA_CIRCLR,
        TacGrpSidc.FSUPP_ARS_C2ARS_TVAR_IRR,
        TacGrpSidc.FSUPP_ARS_C2ARS_TVAR_RTG,
        TacGrpSidc.FSUPP_ARS_C2ARS_TVAR_CIRCLR,
        TacGrpSidc.FSUPP_ARS_C2ARS_TGMF,
        TacGrpSidc.FSUPP_ARS_TGTAQZ_ATIZ_IRR,
        TacGrpSidc.FSUPP_ARS_TGTAQZ_ATIZ_RTG,
        TacGrpSidc.FSUPP_ARS_TGTAQZ_CFFZ_IRR,
        TacGrpSidc.FSUPP_ARS_TGTAQZ_CFFZ_RTG,
        TacGrpSidc.FSUPP_ARS_TGTAQZ_CNS_IRR,
        TacGrpSidc.FSUPP_ARS_TGTAQZ_CNS_RTG,
        TacGrpSidc.FSUPP_ARS_TGTAQZ_CFZ_IRR,
        TacGrpSidc.FSUPP_ARS_TGTAQZ_CFZ_RTG,
        TacGrpSidc.FSUPP_ARS_WPNRF_CIRCLR,
        TacGrpSidc.FSUPP_ARS_WPNRF_SCR,
        TacGrpSidc.FSUPP_ARS_KLBOX_BLUE_CIRCLR,
        TacGrpSidc.FSUPP_ARS_KLBOX_BLUE_IRR,
        TacGrpSidc.FSUPP_ARS_KLBOX_BLUE_RTG,
        TacGrpSidc.FSUPP_ARS_KLBOX_PURPLE_CIRCLR,
        TacGrpSidc.FSUPP_ARS_KLBOX_PURPLE_IRR,
        TacGrpSidc.FSUPP_ARS_KLBOX_PURPLE_RTG,
        TacGrpSidc.CSS_PNT_AEP,
        TacGrpSidc.CSS_PNT_CBNP,
        TacGrpSidc.CSS_PNT_CCP,
        TacGrpSidc.CSS_PNT_CVP,
        TacGrpSidc.CSS_PNT_DCP,
        TacGrpSidc.CSS_PNT_EPWCP,
        TacGrpSidc.CSS_PNT_LRP,
        TacGrpSidc.CSS_PNT_MCP,
        TacGrpSidc.CSS_PNT_RRRP,
        TacGrpSidc.CSS_PNT_ROM,
        TacGrpSidc.CSS_PNT_TCP,
        TacGrpSidc.CSS_PNT_TTP,
        TacGrpSidc.CSS_PNT_UMC,
        TacGrpSidc.CSS_PNT_SPT_GNL,
        TacGrpSidc.CSS_PNT_SPT_CLS1,
        TacGrpSidc.CSS_PNT_SPT_CLS2,
        TacGrpSidc.CSS_PNT_SPT_CLS3,
        TacGrpSidc.CSS_PNT_SPT_CLS4,
        TacGrpSidc.CSS_PNT_SPT_CLS5,
        TacGrpSidc.CSS_PNT_SPT_CLS6,
        TacGrpSidc.CSS_PNT_SPT_CLS7,
        TacGrpSidc.CSS_PNT_SPT_CLS8,
        TacGrpSidc.CSS_PNT_SPT_CLS9,
        TacGrpSidc.CSS_PNT_SPT_CLS10,
        TacGrpSidc.CSS_PNT_AP_ASP,
        TacGrpSidc.CSS_PNT_AP_ATP,
        TacGrpSidc.CSS_ARA_DHA,
        TacGrpSidc.CSS_ARA_EPWHA,
        TacGrpSidc.CSS_ARA_FARP,
        TacGrpSidc.CSS_ARA_RHA,
        TacGrpSidc.CSS_ARA_SUPARS_BSA,
        TacGrpSidc.CSS_ARA_SUPARS_DSA,
        TacGrpSidc.CSS_ARA_SUPARS_RSA,
        TacGrpSidc.OTH_ER_DTHAC,
        TacGrpSidc.OTH_ER_PIW,
        TacGrpSidc.OTH_ER_DSTVES,
        TacGrpSidc.OTH_HAZ_SML,
        TacGrpSidc.OTH_HAZ_IB,
        TacGrpSidc.OTH_HAZ_OLRG,
        TacGrpSidc.OTH_SSUBSR_BTMRTN,
        TacGrpSidc.OTH_SSUBSR_BTMRTN_INS,
        TacGrpSidc.OTH_SSUBSR_BTMRTN_SBRSOO,
        TacGrpSidc.OTH_SSUBSR_BTMRTN_WRKND,
        TacGrpSidc.OTH_SSUBSR_BTMRTN_WRKD,
        TacGrpSidc.OTH_SSUBSR_MARLFE,
        TacGrpSidc.OTH_SSUBSR_SA,
        TacGrpSidc.OTH_FIX_ACU,
        TacGrpSidc.OTH_FIX_EM,
        TacGrpSidc.OTH_FIX_EOP
    };

    /** All graphics from Appendix C that have been implemented. */
    private static final String[] ALL_SUPPORTED_METOC = {
        MetocSidc.AMPHC_PRS_LOWCTR,
        MetocSidc.AMPHC_PRS_LOWCTR_CYC,
        MetocSidc.AMPHC_PRS_LOWCTR_TROPLW,
        MetocSidc.AMPHC_PRS_HGHCTR,
        MetocSidc.AMPHC_PRS_HGHCTR_ACYC,
        MetocSidc.AMPHC_PRS_HGHCTR_TROPHG,
        MetocSidc.AMPHC_TRB_LIT,
        MetocSidc.AMPHC_TRB_MOD,
        MetocSidc.AMPHC_TRB_SVR,
        MetocSidc.AMPHC_TRB_EXT,
        MetocSidc.AMPHC_TRB_MNTWAV,
        MetocSidc.AMPHC_ICG_CLR_LIT,
        MetocSidc.AMPHC_ICG_CLR_MOD,
        MetocSidc.AMPHC_ICG_CLR_SVR,
        MetocSidc.AMPHC_ICG_RIME_LIT,
        MetocSidc.AMPHC_ICG_RIME_MOD,
        MetocSidc.AMPHC_ICG_RIME_SVR,
        MetocSidc.AMPHC_ICG_MIX_LIT,
        MetocSidc.AMPHC_ICG_MIX_MOD,
        MetocSidc.AMPHC_ICG_MIX_SVR,
        MetocSidc.AMPHC_WND_CALM,
        MetocSidc.AMPHC_CUDCOV_SYM_SKC,
        MetocSidc.AMPHC_CUDCOV_SYM_FEW,
        MetocSidc.AMPHC_CUDCOV_SYM_SCT,
        MetocSidc.AMPHC_CUDCOV_SYM_BKN,
        MetocSidc.AMPHC_CUDCOV_SYM_OVC,
        MetocSidc.AMPHC_CUDCOV_SYM_STOPO,
        MetocSidc.AMPHC_WTH_RA_INMLIT,
        MetocSidc.AMPHC_WTH_RA_INMLIT_CTSLIT,
        MetocSidc.AMPHC_WTH_RA_INMMOD,
        MetocSidc.AMPHC_WTH_RA_INMMOD_CTSMOD,
        MetocSidc.AMPHC_WTH_RA_INMHVY,
        MetocSidc.AMPHC_WTH_RA_INMHVY_CTSHVY,
        MetocSidc.AMPHC_WTH_FZRA_LIT,
        MetocSidc.AMPHC_WTH_FZRA_MODHVY,
        MetocSidc.AMPHC_WTH_RASWR_LIT,
        MetocSidc.AMPHC_WTH_RASWR_MODHVY,
        MetocSidc.AMPHC_WTH_RASWR_TOR,
        MetocSidc.AMPHC_WTH_DZ_INMLIT,
        MetocSidc.AMPHC_WTH_DZ_INMLIT_CTSLIT,
        MetocSidc.AMPHC_WTH_DZ_INMMOD,
        MetocSidc.AMPHC_WTH_DZ_INMMOD_CTSMOD,
        MetocSidc.AMPHC_WTH_DZ_INMHVY,
        MetocSidc.AMPHC_WTH_DZ_INMHVY_CTSHVY,
        MetocSidc.AMPHC_WTH_FZDZ_LIT,
        MetocSidc.AMPHC_WTH_FZDZ_MODHVY,
        MetocSidc.AMPHC_WTH_RASN_RDSLIT,
        MetocSidc.AMPHC_WTH_RASN_RDSMH,
        MetocSidc.AMPHC_WTH_RASN_SWRLIT,
        MetocSidc.AMPHC_WTH_RASN_SWRMOD,
        MetocSidc.AMPHC_WTH_SN_INMLIT,
        MetocSidc.AMPHC_WTH_SN_INMLIT_CTSLIT,
        MetocSidc.AMPHC_WTH_SN_INMMOD,
        MetocSidc.AMPHC_WTH_SN_INMMOD_CTSMOD,
        MetocSidc.AMPHC_WTH_SN_INMHVY,
        MetocSidc.AMPHC_WTH_SN_INMHVY_CTSHVY,
        MetocSidc.AMPHC_WTH_SN_BLSNLM,
        MetocSidc.AMPHC_WTH_SN_BLSNHY,
        MetocSidc.AMPHC_WTH_SG,
        MetocSidc.AMPHC_WTH_SSWR_LIT,
        MetocSidc.AMPHC_WTH_SSWR_MODHVY,
        MetocSidc.AMPHC_WTH_HL_LIT,
        MetocSidc.AMPHC_WTH_HL_MODHVY,
        MetocSidc.AMPHC_WTH_IC,
        MetocSidc.AMPHC_WTH_PE_LIT,
        MetocSidc.AMPHC_WTH_PE_MOD,
        MetocSidc.AMPHC_WTH_PE_HVY,
        MetocSidc.AMPHC_WTH_STMS_TS,
        MetocSidc.AMPHC_WTH_STMS_TSLMNH,
        MetocSidc.AMPHC_WTH_STMS_TSHVNH,
        MetocSidc.AMPHC_WTH_STMS_TSLMWH,
        MetocSidc.AMPHC_WTH_STMS_TSHVWH,
        MetocSidc.AMPHC_WTH_STMS_FC,
        MetocSidc.AMPHC_WTH_STMS_SQL,
        MetocSidc.AMPHC_WTH_STMS_LTG,
        MetocSidc.AMPHC_WTH_FG_SHWPTH,
        MetocSidc.AMPHC_WTH_FG_SHWCTS,
        MetocSidc.AMPHC_WTH_FG_PTHY,
        MetocSidc.AMPHC_WTH_FG_SKYVSB,
        MetocSidc.AMPHC_WTH_FG_SKYOBD,
        MetocSidc.AMPHC_WTH_FG_FZSV,
        MetocSidc.AMPHC_WTH_FG_FZSNV,
        MetocSidc.AMPHC_WTH_MIST,
        MetocSidc.AMPHC_WTH_FU,
        MetocSidc.AMPHC_WTH_HZ,
        MetocSidc.AMPHC_WTH_DTSD_LITMOD,
        MetocSidc.AMPHC_WTH_DTSD_SVR,
        MetocSidc.AMPHC_WTH_DTSD_DTDVL,
        MetocSidc.AMPHC_WTH_TPLSYS_TROPDN,
        MetocSidc.AMPHC_WTH_TPLSYS_TROPSM,
        MetocSidc.AMPHC_WTH_TPLSYS_HC,
        MetocSidc.AMPHC_WTH_VOLERN,
        MetocSidc.AMPHC_WTH_VOLERN_VOLASH,
        MetocSidc.AMPHC_WTH_TROPLV,
        MetocSidc.AMPHC_WTH_FZLVL,
        MetocSidc.AMPHC_WTH_POUTAI,
        MetocSidc.AMPHC_STOG_WOSMIC_GLZGRD,
        MetocSidc.AMPHC_STOG_WOSMIC_EXTDWC,
        MetocSidc.AMPHC_STOG_WSMIC_PDMIC,
        MetocSidc.AMPHC_STOG_WSMIC_SCGC,
        MetocSidc.OCA_ISYS_IB,
        MetocSidc.OCA_ISYS_IB_MNY,
        MetocSidc.OCA_ISYS_IB_BAS,
        MetocSidc.OCA_ISYS_IB_GNL,
        MetocSidc.OCA_ISYS_IB_MNYGNL,
        MetocSidc.OCA_ISYS_IB_BB,
        MetocSidc.OCA_ISYS_IB_MNYBB,
        MetocSidc.OCA_ISYS_IB_GWL,
        MetocSidc.OCA_ISYS_IB_MNYGWL,
        MetocSidc.OCA_ISYS_IB_FBG,
        MetocSidc.OCA_ISYS_IB_II,
        MetocSidc.OCA_ISYS_ICN_BW,
        MetocSidc.OCA_ISYS_ICN_WWRT,
        MetocSidc.OCA_ISYS_ICN_IF,
        MetocSidc.OCA_ISYS_DYNPRO_CNG,
        MetocSidc.OCA_ISYS_DYNPRO_DVG,
        MetocSidc.OCA_ISYS_DYNPRO_SHAZ,
        MetocSidc.OCA_ISYS_SI,
        MetocSidc.OCA_ISYS_SI_MPOFI,
        MetocSidc.OCA_ISYS_SC,
        MetocSidc.OCA_ISYS_SC_SWO,
        MetocSidc.OCA_ISYS_TOPFTR_HUM,
        MetocSidc.OCA_ISYS_TOPFTR_RFTG,
        MetocSidc.OCA_ISYS_TOPFTR_JBB,
        MetocSidc.OCA_HYDGRY_DPH_SNDG,
        MetocSidc.OCA_HYDGRY_PRTHBR_PRT_BRHSO,
        MetocSidc.OCA_HYDGRY_PRTHBR_PRT_BRHSA,
        MetocSidc.OCA_HYDGRY_PRTHBR_PRT_ANCRG1,
        MetocSidc.OCA_HYDGRY_PRTHBR_PRT_CIP,
        MetocSidc.OCA_HYDGRY_PRTHBR_FSG_FSGHBR,
        MetocSidc.OCA_HYDGRY_PRTHBR_FSG_FSTK1,
        MetocSidc.OCA_HYDGRY_PRTHBR_FAC_LNDPLC,
        MetocSidc.OCA_HYDGRY_PRTHBR_FAC_OSLF1,
        MetocSidc.OCA_HYDGRY_PRTHBR_FAC_LNDRNG,
        MetocSidc.OCA_HYDGRY_PRTHBR_FAC_DOPN,
        MetocSidc.OCA_HYDGRY_ATN_BCN,
        MetocSidc.OCA_HYDGRY_ATN_BUOY,
        MetocSidc.OCA_HYDGRY_ATN_MRK,
        MetocSidc.OCA_HYDGRY_ATN_PRH1_PRH2,
        MetocSidc.OCA_HYDGRY_ATN_LIT,
        MetocSidc.OCA_HYDGRY_ATN_LITVES,
        MetocSidc.OCA_HYDGRY_ATN_LITHSE,
        MetocSidc.OCA_HYDGRY_DANHAZ_RCKSBM,
        MetocSidc.OCA_HYDGRY_DANHAZ_RCKAWD,
        MetocSidc.OCA_HYDGRY_DANHAZ_FLGRD1_FLGRD2,
        MetocSidc.OCA_HYDGRY_DANHAZ_KLP1_KLP2,
        MetocSidc.OCA_HYDGRY_DANHAZ_MNENAV_DBT,
        MetocSidc.OCA_HYDGRY_DANHAZ_MNENAV_DEFN,
        MetocSidc.OCA_HYDGRY_DANHAZ_SNAG,
        MetocSidc.OCA_HYDGRY_DANHAZ_WRK_UCOV,
        MetocSidc.OCA_HYDGRY_DANHAZ_WRK_SBM,
        MetocSidc.OCA_HYDGRY_DANHAZ_EOTR,
        MetocSidc.OCA_HYDGRY_BTMFAT_BTMCHR_SD,
        MetocSidc.OCA_HYDGRY_BTMFAT_BTMCHR_MUD,
        MetocSidc.OCA_HYDGRY_BTMFAT_BTMCHR_CLAY,
        MetocSidc.OCA_HYDGRY_BTMFAT_BTMCHR_SLT,
        MetocSidc.OCA_HYDGRY_BTMFAT_BTMCHR_STNE,
        MetocSidc.OCA_HYDGRY_BTMFAT_BTMCHR_GVL,
        MetocSidc.OCA_HYDGRY_BTMFAT_BTMCHR_PBL,
        MetocSidc.OCA_HYDGRY_BTMFAT_BTMCHR_COBL,
        MetocSidc.OCA_HYDGRY_BTMFAT_BTMCHR_RCK,
        MetocSidc.OCA_HYDGRY_BTMFAT_BTMCHR_CRL,
        MetocSidc.OCA_HYDGRY_BTMFAT_BTMCHR_SHE,
        MetocSidc.OCA_HYDGRY_BTMFAT_QLFYTM_FNE,
        MetocSidc.OCA_HYDGRY_BTMFAT_QLFYTM_MDM,
        MetocSidc.OCA_HYDGRY_BTMFAT_QLFYTM_CSE,
        MetocSidc.OCA_HYDGRY_TDECUR_H2OTRB,
        MetocSidc.OCA_HYDGRY_TDECUR_TDEDP,
        MetocSidc.OCA_HYDGRY_TDECUR_TDEG,
        MetocSidc.OCA_MMD_FRD,
        MetocSidc.OCA_MMD_LCK,
        MetocSidc.OCA_MMD_OLRG,
        MetocSidc.OCA_MMD_PLE
    };

    /** All graphics from Appendix G that have been implemented. */
    private static final String[] ALL_SUPPORTED_EMS = {
        EmsSidc.NATEVT_GEO_AFTSHK,
        EmsSidc.NATEVT_GEO_AVL,
        EmsSidc.NATEVT_GEO_EQKEPI,
        EmsSidc.NATEVT_GEO_LNDSLD,
        EmsSidc.NATEVT_GEO_SBSDNC,
        EmsSidc.NATEVT_GEO_VLCTHT,
        EmsSidc.NATEVT_HYDMET_DRGHT,
        EmsSidc.NATEVT_HYDMET_FLD,
        EmsSidc.NATEVT_HYDMET_INV,
        EmsSidc.NATEVT_HYDMET_TSNMI,
        EmsSidc.NATEVT_INFST_BIRD,
        EmsSidc.NATEVT_INFST_INSCT,
        EmsSidc.NATEVT_INFST_MICROB,
        EmsSidc.NATEVT_INFST_REPT,
        EmsSidc.NATEVT_INFST_RDNT
    };
}
