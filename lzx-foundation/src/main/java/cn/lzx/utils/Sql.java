package cn.lzx.utils;

import lombok.Data;

/**
 * @author lzx
 * @since 2023/7/4
 */
@Data
public class Sql {

    String H01_01 = "IF EXISTS (SELECT * FROM sys.objects WHERE name='TmpMOR') DROP TABLE TmpMOR";
    String H01_02 = "WITH     FILTER_COND\n" +
            "AS       (SELECT A.ITEM_SEQ,\n" +
            "                 A.MO_DOC_NO,\n" +
            "                 A.OPERATION_SEQ,\n" +
            "                 A.OPERATION_CODE,\n" +
            "                 A.SEQ + '1' AS SEQ,\n" +
            "                 A.DATE,\n" +
            "                 A.SUB_ITEM_ID,\n" +
            "                 SUB_ITEM_CODE,\n" +
            "                 A.SUB_ITEM_FEATURE_ID,\n" +
            "                 A.SUB_ITEM_FEATURE_CODE,\n" +
            "                 A.SUB_DICIMAL_DIGIT,\n" +
            "                 A.DAILY_SHORTAGE AS QTY,\n" +
            "                 'SUB_ITEM_SHORTAGE' AS ROW_TYPE,\n" +
            "                 A.DOC_TYPE,\n" +
            "                 A.DAILY_SHORTAGE\n" +
            "          FROM   QL_BALANCE_MO_R AS A\n" +
            "                 INNER JOIN\n" +
            "                 (SELECT DISTINCT SUB_ITEM_ID,\n" +
            "                                  SUB_ITEM_FEATURE_ID,\n" +
            "                                  MO_DOC_NO\n" +
            "                  FROM   QL_BALANCE_MO_R WITH (NOLOCK)\n" +
            "                  WHERE  ROW_TYPE = 'SUB_ITEM'\n" +
            "                         AND DPP_PROD_BATCH_RESULT_ID = '14682198-b51d-4501-acec-2674df28512f') AS T\n" +
            "                 ON A.SUB_ITEM_ID = T.SUB_ITEM_ID\n" +
            "                    AND A.SUB_ITEM_FEATURE_ID = T.SUB_ITEM_FEATURE_ID\n" +
            "                    AND A.MO_DOC_NO = T.MO_DOC_NO\n" +
            "          WHERE  ROW_TYPE = 'SUB_ITEM'\n" +
            "                 AND DPP_PROD_BATCH_RESULT_ID = '14682198-b51d-4501-acec-2674df28512f'),\n" +
            "         MO_R\n" +
            "AS       (SELECT   M.DOC_DATE,\n" +
            "                   MO_DOC_NO,\n" +
            "                   SEQ,\n" +
            "                   SUB_ITEM_ID,\n" +
            "                   SUB_ITEM_CODE,\n" +
            "                   SUB_ITEM_FEATURE_ID,\n" +
            "                   SUB_DICIMAL_DIGIT,\n" +
            "                   ROW_TYPE,\n" +
            "                   DOC_TYPE,\n" +
            "                   OPERATION_SEQ,\n" +
            "                   OPERATION_CODE,\n" +
            "                   0 AS SELECTED,\n" +
            "                   0 AS VIEW_SELECTED,\n" +
            "                   SUM([1970/10/01 00:00:00]) * (-1) AS [DATE_1970-10-01],\n" +
            "                   SUM([2023/06/12 00:00:00]) * (-1) AS [DATE_2023-06-12],\n" +
            "                   SUM([2023/06/14 00:00:00]) * (-1) AS [DATE_2023-06-14],\n" +
            "                   SUM([2023/06/15 00:00:00]) * (-1) AS [DATE_2023-06-15],\n" +
            "                   SUM([2023/06/17 00:00:00]) * (-1) AS [DATE_2023-06-17],\n" +
            "                   SUM([2023/06/19 00:00:00]) * (-1) AS [DATE_2023-06-19],\n" +
            "                   SUM([2023/06/20 00:00:00]) * (-1) AS [DATE_2023-06-20],\n" +
            "                   SUM([2023/06/21 00:00:00]) * (-1) AS [DATE_2023-06-21],\n" +
            "                   SUM([2023/06/23 00:00:00]) * (-1) AS [DATE_2023-06-23],\n" +
            "                   SUM([2023/06/24 00:00:00]) * (-1) AS [DATE_2023-06-24],\n" +
            "                   SUM([2023/06/26 00:00:00]) * (-1) AS [DATE_2023-06-26],\n" +
            "                   SUM([2023/06/27 00:00:00]) * (-1) AS [DATE_2023-06-27],\n" +
            "                   SUM([2023/06/28 00:00:00]) * (-1) AS [DATE_2023-06-28],\n" +
            "                   SUM([2023/06/29 00:00:00]) * (-1) AS [DATE_2023-06-29],\n" +
            "                   SUM([2023/06/30 00:00:00]) * (-1) AS [DATE_2023-06-30],\n" +
            "                   SUM([2023/07/01 00:00:00]) * (-1) AS [DATE_2023-07-01],\n" +
            "                   SUM([2023/07/03 00:00:00]) * (-1) AS [DATE_2023-07-03],\n" +
            "                   SUM([2023/07/04 00:00:00]) * (-1) AS [DATE_2023-07-04],\n" +
            "                   SUM([2023/07/05 00:00:00]) * (-1) AS [DATE_2023-07-05],\n" +
            "                   SUM([2023/07/06 00:00:00]) * (-1) AS [DATE_2023-07-06],\n" +
            "                   SUM([2023/07/07 00:00:00]) * (-1) AS [DATE_2023-07-07],\n" +
            "                   SUM([2023/07/08 00:00:00]) * (-1) AS [DATE_2023-07-08],\n" +
            "                   SUM([2023/07/10 00:00:00]) * (-1) AS [DATE_2023-07-10],\n" +
            "                   SUM([2023/07/11 00:00:00]) * (-1) AS [DATE_2023-07-11],\n" +
            "                   SUM([2023/07/12 00:00:00]) * (-1) AS [DATE_2023-07-12],\n" +
            "                   SUM([2023/07/13 00:00:00]) * (-1) AS [DATE_2023-07-13],\n" +
            "                   SUM([2023/07/14 00:00:00]) * (-1) AS [DATE_2023-07-14],\n" +
            "                   SUM([2023/07/15 00:00:00]) * (-1) AS [DATE_2023-07-15],\n" +
            "                   SUM([2023/07/17 00:00:00]) * (-1) AS [DATE_2023-07-17],\n" +
            "                   SUM([2023/07/18 00:00:00]) * (-1) AS [DATE_2023-07-18],\n" +
            "                   SUM([2023/07/19 00:00:00]) * (-1) AS [DATE_2023-07-19],\n" +
            "                   SUM([2023/07/20 00:00:00]) * (-1) AS [DATE_2023-07-20],\n" +
            "                   SUM([2023/07/21 00:00:00]) * (-1) AS [DATE_2023-07-21],\n" +
            "                   SUM([2023/07/22 00:00:00]) * (-1) AS [DATE_2023-07-22],\n" +
            "                   SUM([2023/07/24 00:00:00]) * (-1) AS [DATE_2023-07-24],\n" +
            "                   SUM([2023/07/25 00:00:00]) * (-1) AS [DATE_2023-07-25],\n" +
            "                   SUM([2023/07/26 00:00:00]) * (-1) AS [DATE_2023-07-26],\n" +
            "                   SUM([2023/07/27 00:00:00]) * (-1) AS [DATE_2023-07-27],\n" +
            "                   SUM([2023/07/28 00:00:00]) * (-1) AS [DATE_2023-07-28],\n" +
            "                   SUM([2023/07/29 00:00:00]) * (-1) AS [DATE_2023-07-29],\n" +
            "                   SUM([2023/07/31 00:00:00]) * (-1) AS [DATE_2023-07-31],\n" +
            "                   SUM([2023/08/01 00:00:00]) * (-1) AS [DATE_2023-08-01],\n" +
            "                   SUM([2023/08/02 00:00:00]) * (-1) AS [DATE_2023-08-02],\n" +
            "                   SUM([2023/08/03 00:00:00]) * (-1) AS [DATE_2023-08-03],\n" +
            "                   SUM([2023/08/04 00:00:00]) * (-1) AS [DATE_2023-08-04],\n" +
            "                   SUM([2023/08/05 00:00:00]) * (-1) AS [DATE_2023-08-05],\n" +
            "                   SUM([2023/08/07 00:00:00]) * (-1) AS [DATE_2023-08-07],\n" +
            "                   SUM([2023/08/08 00:00:00]) * (-1) AS [DATE_2023-08-08],\n" +
            "                   SUM([2023/08/09 00:00:00]) * (-1) AS [DATE_2023-08-09],\n" +
            "                   SUM([2023/08/10 00:00:00]) * (-1) AS [DATE_2023-08-10],\n" +
            "                   SUM([2023/08/11 00:00:00]) * (-1) AS [DATE_2023-08-11],\n" +
            "                   SUM([2023/08/12 00:00:00]) * (-1) AS [DATE_2023-08-12]\n" +
            "          FROM     (SELECT MO_DOC_NO,\n" +
            "                           OPERATION_SEQ,\n" +
            "                           OPERATION_CODE,\n" +
            "                           SEQ,\n" +
            "                           DATE,\n" +
            "                           SUB_ITEM_ID,\n" +
            "                           SUB_ITEM_CODE,\n" +
            "                           SUB_ITEM_FEATURE_ID,\n" +
            "                           SUB_ITEM_FEATURE_CODE,\n" +
            "                           SUB_DICIMAL_DIGIT,\n" +
            "                           DAILY_SHORTAGE AS QTY,\n" +
            "                           ROW_TYPE,\n" +
            "                           DOC_TYPE\n" +
            "                    FROM   QL_BALANCE_MO_R WITH (NOLOCK)\n" +
            "                    WHERE  ROW_TYPE = 'MAIN_ITEM'\n" +
            "                           AND DPP_PROD_BATCH_RESULT_ID = '14682198-b51d-4501-acec-2674df28512f'\n" +
            "                           AND ITEM_SEQ IN (SELECT ITEM_SEQ\n" +
            "                                            FROM   FILTER_COND)\n" +
            "                           AND MO_DOC_NO IN (SELECT MO_DOC_NO\n" +
            "                                             FROM   FILTER_COND)\n" +
            "                    UNION ALL\n" +
            "                    SELECT MO_DOC_NO,\n" +
            "                           OPERATION_SEQ,\n" +
            "                           OPERATION_CODE,\n" +
            "                           SEQ,\n" +
            "                           DATE,\n" +
            "                           SUB_ITEM_ID,\n" +
            "                           SUB_ITEM_CODE,\n" +
            "                           SUB_ITEM_FEATURE_ID,\n" +
            "                           SUB_ITEM_FEATURE_CODE,\n" +
            "                           SUB_DICIMAL_DIGIT,\n" +
            "                           DAILY_SHORTAGE AS QTY,\n" +
            "                           ROW_TYPE,\n" +
            "                           DOC_TYPE\n" +
            "                    FROM   FILTER_COND) AS MR PIVOT (MAX (QTY) FOR DATE IN ([1970/10/01 00:00:00], [2023/06/12 00:00:00], [2023/06/14 00:00:00], [2023/06/15 00:00:00], [2023/06/17 00:00:00], [2023/06/19 00:00:00], [2023/06/20 00:00:00], [2023/06/21 00:00:00], [2023/06/23 00:00:00], [2023/06/24 00:00:00], [2023/06/26 00:00:00], [2023/06/27 00:00:00], [2023/06/28 00:00:00], [2023/06/29 00:00:00], [2023/06/30 00:00:00], [2023/07/01 00:00:00], [2023/07/03 00:00:00], [2023/07/04 00:00:00], [2023/07/05 00:00:00], [2023/07/06 00:00:00], [2023/07/07 00:00:00], [2023/07/08 00:00:00], [2023/07/10 00:00:00], [2023/07/11 00:00:00], [2023/07/12 00:00:00], [2023/07/13 00:00:00], [2023/07/14 00:00:00], [2023/07/15 00:00:00], [2023/07/17 00:00:00], [2023/07/18 00:00:00], [2023/07/19 00:00:00], [2023/07/20 00:00:00], [2023/07/21 00:00:00], [2023/07/22 00:00:00], [2023/07/24 00:00:00], [2023/07/25 00:00:00], [2023/07/26 00:00:00], [2023/07/27 00:00:00], [2023/07/28 00:00:00], [2023/07/29 00:00:00], [2023/07/31 00:00:00], [2023/08/01 00:00:00], [2023/08/02 00:00:00], [2023/08/03 00:00:00], [2023/08/04 00:00:00], [2023/08/05 00:00:00], [2023/08/07 00:00:00], [2023/08/08 00:00:00], [2023/08/09 00:00:00], [2023/08/10 00:00:00], [2023/08/11 00:00:00], [2023/08/12 00:00:00])) AS MOR\n" +
            "                   LEFT OUTER JOIN\n" +
            "                   [HT].dbo.MO AS M\n" +
            "                   ON MOR.MO_DOC_NO = M.DOC_NO\n" +
            "          GROUP BY SEQ, SUB_ITEM_ID, SUB_ITEM_CODE, SUB_ITEM_FEATURE_ID, SUB_ITEM_FEATURE_CODE, SUB_DICIMAL_DIGIT, ROW_TYPE, OPERATION_SEQ, OPERATION_CODE, DOC_TYPE, MO_DOC_NO, M.DOC_DATE)\n" +
            "SELECT   *,\n" +
            "         ROW_NUMBER() OVER (ORDER BY MO_DOC_NO, OPERATION_SEQ, OPERATION_CODE, SEQ) AS ROW_NUM\n" +
            "INTO     TmpMOR\n" +
            "FROM     MO_R\n" +
            "WHERE    1 = 1\n" +
            "ORDER BY MO_DOC_NO, OPERATION_SEQ, OPERATION_CODE, SEQ";
    String H01_03 = "SELECT * FROM   TmpMOR";
    String H02_01 = "IF EXISTS (SELECT * FROM sys.objects WHERE name='TmpMOL') DROP TABLE TmpMOL";
    String H02_02 = "SELECT DISTINCT B.CONFIRMATION,\n" +
            "                B.DEMAND_NO,\n" +
            "                B.DICIMAL_DIGIT,\n" +
            "                B.DOC_DATE,\n" +
            "                B.DOC_NO_SEQ,\n" +
            "                B.DOC_TYPE,\n" +
            "                B.DPP_PROD_BATCH_RESULT_ID,\n" +
            "                B.DRAWING_NO,\n" +
            "                B.IS_PRODUCTION_SEQ,\n" +
            "                B.ITEM_CODE,\n" +
            "                B.ITEM_DESC,\n" +
            "                B.ITEM_FEATURE_CODE,\n" +
            "                B.ITEM_FEATURE_ID,\n" +
            "                B.ITEM_FEATURE_SPEC,\n" +
            "                B.ITEM_ID,\n" +
            "                B.ITEM_NAME,\n" +
            "                B.ITEM_SPEC,\n" +
            "                B.LOW_LEVEL_CODE,\n" +
            "                B.MAIN_SUPPLIER_CODE,\n" +
            "                B.MAIN_SUPPLIER_NAME,\n" +
            "                B.MAX_DATE,\n" +
            "                B.MIN_DATE,\n" +
            "                B.MO_DOC_NO,\n" +
            "                B.MO_ID,\n" +
            "                B.NEW_START_DATE,\n" +
            "                B.OPERATION_CODE,\n" +
            "                B.OPERATION_NAME,\n" +
            "                B.OPERATION_SEQ,\n" +
            "                B.PROD_QTY,\n" +
            "                B.PS_TYPE_CODE,\n" +
            "                B.PS_TYPE_ID,\n" +
            "                B.PS_TYPE_NAME,\n" +
            "                B.REMARK,\n" +
            "                CASE WHEN B.ROW_TYPE = 'SUB_ITEM' THEN 'SUB_ITEM_SHORTAGE'\n" +
            "                     ELSE B.ROW_TYPE END AS ROW_TYPE,\n" +
            "                B.SEQ,\n" +
            "                B.SHORTCUT,\n" +
            "                B.SUB_DICIMAL_DIGIT,\n" +
            "                B.SUB_ITEM_CODE,\n" +
            "                B.SUB_ITEM_DESC,\n" +
            "                B.SUB_ITEM_FEATURE_CODE,\n" +
            "                B.SUB_ITEM_FEATURE_ID,\n" +
            "                B.SUB_ITEM_FEATURE_SPEC,\n" +
            "                B.SUB_ITEM_ID,\n" +
            "                B.SUB_ITEM_NAME,\n" +
            "                B.SUB_ITEM_SPEC,\n" +
            "                B.SUB_UNIT_CODE,\n" +
            "                B.TOTAL_DEMAND_QTY,\n" +
            "                B.TOTAL_OWE_QTY,\n" +
            "                B.TOTAL_SCHED_QTY,\n" +
            "                B.TOTAL_SHORTAGE_QTY,\n" +
            "                B.UNIT_CODE,\n" +
            "                B.QL_BALANCE_MO_L_ID,\n" +
            "                B.ROW_NUM AS OLD_ROW_NUM,\n" +
            "                B.SELECTED,\n" +
            "                B.VIEW_SELECTED,\n" +
            "                CASE WHEN C.SOURCE_ID_RTK = 'SUPPLIER'\n" +
            "                          OR SUGGESTION_PLAN.SOURCE_ID_RTK = 'SUPPLIER' THEN CASE WHEN ISNULL(ISNULL(MO_SP.SUPPLIER_CODE, SU_SP.SUPPLIER_CODE), '') = '' THEN ''\n" +
            "                                                                                  ELSE CASE WHEN ISNULL(MO_SP.SUPPLIER_CODE, '') = '' THEN SU_SP.SUPPLIER_NAME + '(' + SU_SP.SUPPLIER_CODE + ')'\n" +
            "                                                                                            ELSE MO_SP.SUPPLIER_NAME + '(' + MO_SP.SUPPLIER_CODE + ')' END END\n" +
            "                     ELSE CASE WHEN ISNULL(ISNULL(MO_WC.WORK_CENTER_CODE, SU_WC.WORK_CENTER_CODE), '') = '' THEN ''\n" +
            "                               ELSE CASE WHEN ISNULL(MO_WC.WORK_CENTER_CODE, '') = '' THEN SU_WC.WORK_CENTER_NAME + '(' + SU_WC.WORK_CENTER_CODE + ')'\n" +
            "                                         ELSE MO_WC.WORK_CENTER_NAME + '(' + MO_WC.WORK_CENTER_CODE + ')' END END END AS WORK_CENTER,\n" +
            "                ISNULL(ISNULL(SU_ITEM.ITEM_CODE, MO_ITEM.ITEM_CODE), B.ITEM_CODE) AS CP_ITEM_CODE,\n" +
            "                ISNULL(ISNULL(SU_ITEM.ITEM_NAME, MO_ITEM.ITEM_NAME), B.ITEM_NAME) AS CP_ITEM_NAME,\n" +
            "                ISNULL(ISNULL(SU_ITEM.ITEM_SPECIFICATION, MO_ITEM.ITEM_SPECIFICATION), B.ITEM_SPEC) AS CP_ITEM_SPECIFICATION\n" +
            "INTO   TmpMOL\n" +
            "FROM   TmpMOR AS A\n" +
            "       LEFT OUTER JOIN\n" +
            "       QL_BALANCE_MO_L AS B\n" +
            "       ON A.MO_DOC_NO = B.DOC_NO_SEQ\n" +
            "          AND A.SUB_ITEM_CODE = B.SUB_ITEM_CODE\n" +
            "          AND A.SEQ = B.SEQ\n" +
            "       LEFT OUTER JOIN\n" +
            "       [HT].dbo.MO AS C\n" +
            "       ON B.MO_ID = C.MO_ID\n" +
            "       LEFT OUTER JOIN\n" +
            "       [HT].dbo.SUPPLIER AS MO_SP\n" +
            "       ON MO_SP.SUPPLIER_BUSINESS_ID = C.SOURCE_ID_ROid\n" +
            "          AND C.SOURCE_ID_RTK = 'SUPPLIER'\n" +
            "       LEFT OUTER JOIN\n" +
            "       [HT].dbo.WORK_CENTER AS MO_WC\n" +
            "       ON C.SOURCE_ID_ROid = MO_WC.WORK_CENTER_ID\n" +
            "          AND C.SOURCE_ID_RTK = 'WORK_CENTER'\n" +
            "       LEFT OUTER JOIN\n" +
            "       [HT].dbo.ITEM AS MO_ITEM\n" +
            "       ON C.ITEM_ID = MO_ITEM.ITEM_BUSINESS_ID\n" +
            "       LEFT OUTER JOIN\n" +
            "       [HT].dbo.SUGGESTION_PLAN AS SUGGESTION_PLAN\n" +
            "       ON SUGGESTION_PLAN.APS_DOC_NO = B.MO_DOC_NO\n" +
            "       LEFT OUTER JOIN\n" +
            "       [HT].dbo.WORK_CENTER AS SU_WC\n" +
            "       ON SUGGESTION_PLAN.SOURCE_ID_RTK = 'WORK_CENTER'\n" +
            "          AND SUGGESTION_PLAN.SOURCE_ID_ROid = SU_WC.WORK_CENTER_ID\n" +
            "       LEFT OUTER JOIN\n" +
            "       [HT].dbo.SUPPLIER AS SU_SP\n" +
            "       ON SUGGESTION_PLAN.SOURCE_ID_RTK = 'SUPPLIER'\n" +
            "          AND SUGGESTION_PLAN.SOURCE_ID_ROid = SU_SP.SUPPLIER_BUSINESS_ID\n" +
            "       LEFT OUTER JOIN\n" +
            "       [HT].dbo.ITEM AS SU_ITEM\n" +
            "       ON SUGGESTION_PLAN.ITEM_ID = SU_ITEM.ITEM_BUSINESS_ID\n" +
            "WHERE  DPP_PROD_BATCH_RESULT_ID = '14682198-b51d-4501-acec-2674df28512f'";
    String H02_03 = "SELECT   *,\n" +
            "         ROW_NUMBER() OVER (ORDER BY OLD_ROW_NUM) AS ROW_NUM\n" +
            "FROM     TmpMOL\n" +
            "ORDER BY OLD_ROW_NUM";

    String H03_01 = "IF EXISTS (SELECT * FROM sys.objects WHERE name='TmpITEMR') DROP TABLE TmpITEMR";
    String H03_02 = "WITH   FILTER_COND\n" +
            "AS     (SELECT DISTINCT SUB_ITEM_ID,\n" +
            "                        SUB_ITEM_FEATURE_ID\n" +
            "        FROM   QL_BALANCE_ITEM_R WITH (NOLOCK)\n" +
            "        WHERE  DPP_PROD_BATCH_RESULT_ID = '14682198-b51d-4501-acec-2674df28512f'),\n" +
            "       ITEM_R\n" +
            "AS     (SELECT   SUB_ITEM_ID,\n" +
            "                 SUB_ITEM_CODE,\n" +
            "                 SUB_ITEM_NAME,\n" +
            "                 SUB_ITEM_SPEC,\n" +
            "                 SUB_ITEM_FEATURE_ID,\n" +
            "                 SUB_ITEM_FEATURE_CODE,\n" +
            "                 SUB_ITEM_FEATURE_SPEC,\n" +
            "                 SUB_UNIT_CODE,\n" +
            "                 SUB_DICIMAL_DIGIT,\n" +
            "                 ROW_TYPE,\n" +
            "                 TYPE,\n" +
            "                 0 AS SELECTED,\n" +
            "                 0 AS VIEW_SELECTED,\n" +
            "                 SUM([1970/10/01 00:00:00]) * (-1) AS [DATE_1970-10-01],\n" +
            "                 SUM([2023/06/12 00:00:00]) * (-1) AS [DATE_2023-06-12],\n" +
            "                 SUM([2023/06/14 00:00:00]) * (-1) AS [DATE_2023-06-14],\n" +
            "                 SUM([2023/06/15 00:00:00]) * (-1) AS [DATE_2023-06-15],\n" +
            "                 SUM([2023/06/17 00:00:00]) * (-1) AS [DATE_2023-06-17],\n" +
            "                 SUM([2023/06/19 00:00:00]) * (-1) AS [DATE_2023-06-19],\n" +
            "                 SUM([2023/06/20 00:00:00]) * (-1) AS [DATE_2023-06-20],\n" +
            "                 SUM([2023/06/21 00:00:00]) * (-1) AS [DATE_2023-06-21],\n" +
            "                 SUM([2023/06/23 00:00:00]) * (-1) AS [DATE_2023-06-23],\n" +
            "                 SUM([2023/06/24 00:00:00]) * (-1) AS [DATE_2023-06-24],\n" +
            "                 SUM([2023/06/26 00:00:00]) * (-1) AS [DATE_2023-06-26],\n" +
            "                 SUM([2023/06/27 00:00:00]) * (-1) AS [DATE_2023-06-27],\n" +
            "                 SUM([2023/06/28 00:00:00]) * (-1) AS [DATE_2023-06-28],\n" +
            "                 SUM([2023/06/29 00:00:00]) * (-1) AS [DATE_2023-06-29],\n" +
            "                 SUM([2023/06/30 00:00:00]) * (-1) AS [DATE_2023-06-30],\n" +
            "                 SUM([2023/07/01 00:00:00]) * (-1) AS [DATE_2023-07-01],\n" +
            "                 SUM([2023/07/03 00:00:00]) * (-1) AS [DATE_2023-07-03],\n" +
            "                 SUM([2023/07/04 00:00:00]) * (-1) AS [DATE_2023-07-04],\n" +
            "                 SUM([2023/07/05 00:00:00]) * (-1) AS [DATE_2023-07-05],\n" +
            "                 SUM([2023/07/06 00:00:00]) * (-1) AS [DATE_2023-07-06],\n" +
            "                 SUM([2023/07/07 00:00:00]) * (-1) AS [DATE_2023-07-07],\n" +
            "                 SUM([2023/07/08 00:00:00]) * (-1) AS [DATE_2023-07-08],\n" +
            "                 SUM([2023/07/10 00:00:00]) * (-1) AS [DATE_2023-07-10],\n" +
            "                 SUM([2023/07/11 00:00:00]) * (-1) AS [DATE_2023-07-11],\n" +
            "                 SUM([2023/07/12 00:00:00]) * (-1) AS [DATE_2023-07-12],\n" +
            "                 SUM([2023/07/13 00:00:00]) * (-1) AS [DATE_2023-07-13],\n" +
            "                 SUM([2023/07/14 00:00:00]) * (-1) AS [DATE_2023-07-14],\n" +
            "                 SUM([2023/07/15 00:00:00]) * (-1) AS [DATE_2023-07-15],\n" +
            "                 SUM([2023/07/17 00:00:00]) * (-1) AS [DATE_2023-07-17],\n" +
            "                 SUM([2023/07/18 00:00:00]) * (-1) AS [DATE_2023-07-18],\n" +
            "                 SUM([2023/07/19 00:00:00]) * (-1) AS [DATE_2023-07-19],\n" +
            "                 SUM([2023/07/20 00:00:00]) * (-1) AS [DATE_2023-07-20],\n" +
            "                 SUM([2023/07/21 00:00:00]) * (-1) AS [DATE_2023-07-21],\n" +
            "                 SUM([2023/07/22 00:00:00]) * (-1) AS [DATE_2023-07-22],\n" +
            "                 SUM([2023/07/24 00:00:00]) * (-1) AS [DATE_2023-07-24],\n" +
            "                 SUM([2023/07/25 00:00:00]) * (-1) AS [DATE_2023-07-25],\n" +
            "                 SUM([2023/07/26 00:00:00]) * (-1) AS [DATE_2023-07-26],\n" +
            "                 SUM([2023/07/27 00:00:00]) * (-1) AS [DATE_2023-07-27],\n" +
            "                 SUM([2023/07/28 00:00:00]) * (-1) AS [DATE_2023-07-28],\n" +
            "                 SUM([2023/07/29 00:00:00]) * (-1) AS [DATE_2023-07-29],\n" +
            "                 SUM([2023/07/31 00:00:00]) * (-1) AS [DATE_2023-07-31],\n" +
            "                 SUM([2023/08/01 00:00:00]) * (-1) AS [DATE_2023-08-01],\n" +
            "                 SUM([2023/08/02 00:00:00]) * (-1) AS [DATE_2023-08-02],\n" +
            "                 SUM([2023/08/03 00:00:00]) * (-1) AS [DATE_2023-08-03],\n" +
            "                 SUM([2023/08/04 00:00:00]) * (-1) AS [DATE_2023-08-04],\n" +
            "                 SUM([2023/08/05 00:00:00]) * (-1) AS [DATE_2023-08-05],\n" +
            "                 SUM([2023/08/07 00:00:00]) * (-1) AS [DATE_2023-08-07],\n" +
            "                 SUM([2023/08/08 00:00:00]) * (-1) AS [DATE_2023-08-08],\n" +
            "                 SUM([2023/08/09 00:00:00]) * (-1) AS [DATE_2023-08-09],\n" +
            "                 SUM([2023/08/10 00:00:00]) * (-1) AS [DATE_2023-08-10],\n" +
            "                 SUM([2023/08/11 00:00:00]) * (-1) AS [DATE_2023-08-11],\n" +
            "                 SUM([2023/08/12 00:00:00]) * (-1) AS [DATE_2023-08-12]\n" +
            "        FROM     (SELECT TEMP_R.SUB_ITEM_ID,\n" +
            "                         TEMP_R.SUB_ITEM_CODE,\n" +
            "                         TEMP_R.SUB_ITEM_NAME,\n" +
            "                         TEMP_R.SUB_ITEM_SPEC,\n" +
            "                         TEMP_R.SUB_ITEM_FEATURE_ID,\n" +
            "                         TEMP_R.SUB_ITEM_FEATURE_CODE,\n" +
            "                         TEMP_R.DATE,\n" +
            "                         TEMP_R.SUB_ITEM_FEATURE_SPEC,\n" +
            "                         ISNULL(UT.UNIT_NAME, '') + '(' + TEMP_R.SUB_UNIT_CODE + ')' AS SUB_UNIT_CODE,\n" +
            "                         TEMP_R.SUB_DICIMAL_DIGIT,\n" +
            "                         TEMP_R.QTY,\n" +
            "                         TEMP_R.ROW_TYPE,\n" +
            "                         TEMP_R.TYPE\n" +
            "                  FROM   (SELECT A.SUB_ITEM_ID,\n" +
            "                                 SUB_ITEM_CODE,\n" +
            "                                 SUB_ITEM_NAME,\n" +
            "                                 SUB_ITEM_SPEC,\n" +
            "                                 A.SUB_ITEM_FEATURE_ID,\n" +
            "                                 SUB_ITEM_FEATURE_CODE,\n" +
            "                                 DATE,\n" +
            "                                 SUB_ITEM_FEATURE_SPEC,\n" +
            "                                 SUB_UNIT_CODE,\n" +
            "                                 SUB_DICIMAL_DIGIT,\n" +
            "                                 (-1) * REQUIRED_QTY AS QTY,\n" +
            "                                 'DEMAND' AS ROW_TYPE,\n" +
            "                                 '需求' AS TYPE\n" +
            "                          FROM   QL_BALANCE_ITEM_R AS A WITH (NOLOCK)\n" +
            "                                 INNER JOIN\n" +
            "                                 FILTER_COND AS B\n" +
            "                                 ON A.SUB_ITEM_ID = B.SUB_ITEM_ID\n" +
            "                                    AND A.SUB_ITEM_FEATURE_ID = B.SUB_ITEM_FEATURE_ID\n" +
            "                          WHERE  DPP_PROD_BATCH_RESULT_ID = '14682198-b51d-4501-acec-2674df28512f'\n" +
            "                          UNION ALL\n" +
            "                          SELECT A.SUB_ITEM_ID,\n" +
            "                                 SUB_ITEM_CODE,\n" +
            "                                 SUB_ITEM_NAME,\n" +
            "                                 SUB_ITEM_SPEC,\n" +
            "                                 A.SUB_ITEM_FEATURE_ID,\n" +
            "                                 SUB_ITEM_FEATURE_CODE,\n" +
            "                                 DATE,\n" +
            "                                 SUB_ITEM_FEATURE_SPEC,\n" +
            "                                 SUB_UNIT_CODE,\n" +
            "                                 SUB_DICIMAL_DIGIT,\n" +
            "                                 OWE_QTY AS QTY,\n" +
            "                                 'OWE' AS ROW_TYPE,\n" +
            "                                 '欠料' AS TYPE\n" +
            "                          FROM   QL_BALANCE_ITEM_R AS A WITH (NOLOCK)\n" +
            "                                 INNER JOIN\n" +
            "                                 FILTER_COND AS B\n" +
            "                                 ON A.SUB_ITEM_ID = B.SUB_ITEM_ID\n" +
            "                                    AND A.SUB_ITEM_FEATURE_ID = B.SUB_ITEM_FEATURE_ID\n" +
            "                          WHERE  DPP_PROD_BATCH_RESULT_ID = '14682198-b51d-4501-acec-2674df28512f'\n" +
            "                          UNION ALL\n" +
            "                          SELECT A.SUB_ITEM_ID,\n" +
            "                                 SUB_ITEM_CODE,\n" +
            "                                 SUB_ITEM_NAME,\n" +
            "                                 SUB_ITEM_SPEC,\n" +
            "                                 A.SUB_ITEM_FEATURE_ID,\n" +
            "                                 SUB_ITEM_FEATURE_CODE,\n" +
            "                                 DATE,\n" +
            "                                 SUB_ITEM_FEATURE_SPEC,\n" +
            "                                 SUB_UNIT_CODE,\n" +
            "                                 SUB_DICIMAL_DIGIT,\n" +
            "                                 (-1) * REPLY_QTY AS QTY,\n" +
            "                                 'REPLY' AS ROW_TYPE,\n" +
            "                                 '回复' AS TYPE\n" +
            "                          FROM   QL_BALANCE_ITEM_R AS A WITH (NOLOCK)\n" +
            "                                 INNER JOIN\n" +
            "                                 FILTER_COND AS B\n" +
            "                                 ON A.SUB_ITEM_ID = B.SUB_ITEM_ID\n" +
            "                                    AND A.SUB_ITEM_FEATURE_ID = B.SUB_ITEM_FEATURE_ID\n" +
            "                          WHERE  DPP_PROD_BATCH_RESULT_ID = '14682198-b51d-4501-acec-2674df28512f'\n" +
            "                          UNION ALL\n" +
            "                          SELECT A.SUB_ITEM_ID,\n" +
            "                                 SUB_ITEM_CODE,\n" +
            "                                 SUB_ITEM_NAME,\n" +
            "                                 SUB_ITEM_SPEC,\n" +
            "                                 A.SUB_ITEM_FEATURE_ID,\n" +
            "                                 SUB_ITEM_FEATURE_CODE,\n" +
            "                                 DATE,\n" +
            "                                 SUB_ITEM_FEATURE_SPEC,\n" +
            "                                 SUB_UNIT_CODE,\n" +
            "                                 SUB_DICIMAL_DIGIT,\n" +
            "                                 SHORTAGE_QTY AS QTY,\n" +
            "                                 'SHORTAGE' AS ROW_TYPE,\n" +
            "                                 '缺口' AS TYPE\n" +
            "                          FROM   QL_BALANCE_ITEM_R AS A WITH (NOLOCK)\n" +
            "                                 INNER JOIN\n" +
            "                                 FILTER_COND AS B\n" +
            "                                 ON A.SUB_ITEM_ID = B.SUB_ITEM_ID\n" +
            "                                    AND A.SUB_ITEM_FEATURE_ID = B.SUB_ITEM_FEATURE_ID\n" +
            "                          WHERE  DPP_PROD_BATCH_RESULT_ID = '14682198-b51d-4501-acec-2674df28512f') AS TEMP_R\n" +
            "                         INNER JOIN\n" +
            "                         (SELECT ITEM_ID,\n" +
            "                                 ITEM_FEATURE_ID\n" +
            "                          FROM   QL_MO_D_ITEM WITH (NOLOCK)\n" +
            "                          UNION\n" +
            "                          SELECT ITEM_ID,\n" +
            "                                 ITEM_FEATURE_ID\n" +
            "                          FROM   QL_ITEM WITH (NOLOCK)) AS TMDI\n" +
            "                         ON TMDI.ITEM_ID = TEMP_R.SUB_ITEM_ID\n" +
            "                            AND TMDI.ITEM_FEATURE_ID = TEMP_R.SUB_ITEM_FEATURE_ID\n" +
            "                         LEFT OUTER JOIN\n" +
            "                         [HT].dbo.UNIT AS UT WITH (NOLOCK)\n" +
            "                         ON UT.UNIT_CODE = TEMP_R.SUB_UNIT_CODE) AS U_ITEM_R PIVOT (MAX (QTY) FOR DATE IN ([1970/10/01 00:00:00], [2023/06/12 00:00:00], [2023/06/14 00:00:00], [2023/06/15 00:00:00], [2023/06/17 00:00:00], [2023/06/19 00:00:00], [2023/06/20 00:00:00], [2023/06/21 00:00:00], [2023/06/23 00:00:00], [2023/06/24 00:00:00], [2023/06/26 00:00:00], [2023/06/27 00:00:00], [2023/06/28 00:00:00], [2023/06/29 00:00:00], [2023/06/30 00:00:00], [2023/07/01 00:00:00], [2023/07/03 00:00:00], [2023/07/04 00:00:00], [2023/07/05 00:00:00], [2023/07/06 00:00:00], [2023/07/07 00:00:00], [2023/07/08 00:00:00], [2023/07/10 00:00:00], [2023/07/11 00:00:00], [2023/07/12 00:00:00], [2023/07/13 00:00:00], [2023/07/14 00:00:00], [2023/07/15 00:00:00], [2023/07/17 00:00:00], [2023/07/18 00:00:00], [2023/07/19 00:00:00], [2023/07/20 00:00:00], [2023/07/21 00:00:00], [2023/07/22 00:00:00], [2023/07/24 00:00:00], [2023/07/25 00:00:00], [2023/07/26 00:00:00], [2023/07/27 00:00:00], [2023/07/28 00:00:00], [2023/07/29 00:00:00], [2023/07/31 00:00:00], [2023/08/01 00:00:00], [2023/08/02 00:00:00], [2023/08/03 00:00:00], [2023/08/04 00:00:00], [2023/08/05 00:00:00], [2023/08/07 00:00:00], [2023/08/08 00:00:00], [2023/08/09 00:00:00], [2023/08/10 00:00:00], [2023/08/11 00:00:00], [2023/08/12 00:00:00])) AS ITR\n" +
            "        GROUP BY SUB_ITEM_ID, SUB_ITEM_CODE, SUB_ITEM_NAME, SUB_ITEM_SPEC, SUB_ITEM_FEATURE_ID, SUB_ITEM_FEATURE_CODE, SUB_ITEM_FEATURE_SPEC, SUB_UNIT_CODE, SUB_DICIMAL_DIGIT, ROW_TYPE, TYPE)\n" +
            "SELECT *,\n" +
            "       ROW_NUMBER() OVER (PARTITION BY ROW_TYPE ORDER BY SUB_ITEM_CODE, SUB_ITEM_FEATURE_CODE) AS ITEM_NUM,\n" +
            "       ROW_NUMBER() OVER (ORDER BY SUB_ITEM_CODE, SUB_ITEM_FEATURE_CODE, ROW_TYPE) AS ROW_NUM\n" +
            "INTO   TmpITEMR\n" +
            "FROM   ITEM_R";
    String H03_03 = "SELECT *\n" +
            "FROM   TmpITEMR";
    String H04_01 = "SELECT B.*,\n" +
            "       ROW_NUMBER() OVER (ORDER BY A.ROW_NUM) AS ROW_NUM_NEW\n" +
            "FROM   TmpITEMR AS A\n" +
            "       LEFT OUTER JOIN\n" +
            "       QL_BALANCE_ITEM_L AS B\n" +
            "       ON A.SUB_ITEM_CODE = B.SUB_ITEM_CODE\n" +
            "          AND A.SUB_ITEM_FEATURE_ID = B.SUB_ITEM_FEATURE_ID\n" +
            "WHERE  DPP_PROD_BATCH_RESULT_ID = '14682198-b51d-4501-acec-2674df28512f'\n" +
            "       AND B.ROW_TYPE = 'SHORTAGE';";
    String H05_01 = "SELECT *\n" +
            "FROM   QL_ITEMD_L\n" +
            "WHERE  DPP_PROD_BATCH_RESULT_ID = '14682198-b51d-4501-acec-2674df28512f';";
    String H06_01 = "SELECT   DOC_NO,\n" +
            "         SEQ,\n" +
            "         OPERATION_SEQ,\n" +
            "         OPERATION_CODE,\n" +
            "         SUB_ITEM_ID,\n" +
            "         SUB_ITEM_CODE,\n" +
            "         SUB_ITEM_FEATURE_ID,\n" +
            "         SUB_DICIMAL_DIGIT,\n" +
            "         ROW_TYPE,\n" +
            "         ROW_NUMBER() OVER (PARTITION BY SUB_ITEM_CODE ORDER BY SEQ, OPERATION_SEQ, OPERATION_CODE) AS ROW_NUM,\n" +
            "         ROW_NUMBER() OVER (PARTITION BY SUB_ITEM_CODE ORDER BY SEQ, OPERATION_SEQ, OPERATION_CODE) AS ROW_NUM_SORT,\n" +
            "         0 AS SELECTED,\n" +
            "         S_TYPE,\n" +
            "         SUM([1970/10/01 00:00:00]) AS [DATE_1970-10-01],\n" +
            "         SUM([2023/06/12 00:00:00]) AS [DATE_2023-06-12],\n" +
            "         SUM([2023/06/14 00:00:00]) AS [DATE_2023-06-14],\n" +
            "         SUM([2023/06/15 00:00:00]) AS [DATE_2023-06-15],\n" +
            "         SUM([2023/06/17 00:00:00]) AS [DATE_2023-06-17],\n" +
            "         SUM([2023/06/19 00:00:00]) AS [DATE_2023-06-19],\n" +
            "         SUM([2023/06/20 00:00:00]) AS [DATE_2023-06-20],\n" +
            "         SUM([2023/06/21 00:00:00]) AS [DATE_2023-06-21],\n" +
            "         SUM([2023/06/23 00:00:00]) AS [DATE_2023-06-23],\n" +
            "         SUM([2023/06/24 00:00:00]) AS [DATE_2023-06-24],\n" +
            "         SUM([2023/06/26 00:00:00]) AS [DATE_2023-06-26],\n" +
            "         SUM([2023/06/27 00:00:00]) AS [DATE_2023-06-27],\n" +
            "         SUM([2023/06/28 00:00:00]) AS [DATE_2023-06-28],\n" +
            "         SUM([2023/06/29 00:00:00]) AS [DATE_2023-06-29],\n" +
            "         SUM([2023/06/30 00:00:00]) AS [DATE_2023-06-30],\n" +
            "         SUM([2023/07/01 00:00:00]) AS [DATE_2023-07-01],\n" +
            "         SUM([2023/07/03 00:00:00]) AS [DATE_2023-07-03],\n" +
            "         SUM([2023/07/04 00:00:00]) AS [DATE_2023-07-04],\n" +
            "         SUM([2023/07/05 00:00:00]) AS [DATE_2023-07-05],\n" +
            "         SUM([2023/07/06 00:00:00]) AS [DATE_2023-07-06],\n" +
            "         SUM([2023/07/07 00:00:00]) AS [DATE_2023-07-07],\n" +
            "         SUM([2023/07/08 00:00:00]) AS [DATE_2023-07-08],\n" +
            "         SUM([2023/07/10 00:00:00]) AS [DATE_2023-07-10],\n" +
            "         SUM([2023/07/11 00:00:00]) AS [DATE_2023-07-11],\n" +
            "         SUM([2023/07/12 00:00:00]) AS [DATE_2023-07-12],\n" +
            "         SUM([2023/07/13 00:00:00]) AS [DATE_2023-07-13],\n" +
            "         SUM([2023/07/14 00:00:00]) AS [DATE_2023-07-14],\n" +
            "         SUM([2023/07/15 00:00:00]) AS [DATE_2023-07-15],\n" +
            "         SUM([2023/07/17 00:00:00]) AS [DATE_2023-07-17],\n" +
            "         SUM([2023/07/18 00:00:00]) AS [DATE_2023-07-18],\n" +
            "         SUM([2023/07/19 00:00:00]) AS [DATE_2023-07-19],\n" +
            "         SUM([2023/07/20 00:00:00]) AS [DATE_2023-07-20],\n" +
            "         SUM([2023/07/21 00:00:00]) AS [DATE_2023-07-21],\n" +
            "         SUM([2023/07/22 00:00:00]) AS [DATE_2023-07-22],\n" +
            "         SUM([2023/07/24 00:00:00]) AS [DATE_2023-07-24],\n" +
            "         SUM([2023/07/25 00:00:00]) AS [DATE_2023-07-25],\n" +
            "         SUM([2023/07/26 00:00:00]) AS [DATE_2023-07-26],\n" +
            "         SUM([2023/07/27 00:00:00]) AS [DATE_2023-07-27],\n" +
            "         SUM([2023/07/28 00:00:00]) AS [DATE_2023-07-28],\n" +
            "         SUM([2023/07/29 00:00:00]) AS [DATE_2023-07-29],\n" +
            "         SUM([2023/07/31 00:00:00]) AS [DATE_2023-07-31],\n" +
            "         SUM([2023/08/01 00:00:00]) AS [DATE_2023-08-01],\n" +
            "         SUM([2023/08/02 00:00:00]) AS [DATE_2023-08-02],\n" +
            "         SUM([2023/08/03 00:00:00]) AS [DATE_2023-08-03],\n" +
            "         SUM([2023/08/04 00:00:00]) AS [DATE_2023-08-04],\n" +
            "         SUM([2023/08/05 00:00:00]) AS [DATE_2023-08-05],\n" +
            "         SUM([2023/08/07 00:00:00]) AS [DATE_2023-08-07],\n" +
            "         SUM([2023/08/08 00:00:00]) AS [DATE_2023-08-08],\n" +
            "         SUM([2023/08/09 00:00:00]) AS [DATE_2023-08-09],\n" +
            "         SUM([2023/08/10 00:00:00]) AS [DATE_2023-08-10],\n" +
            "         SUM([2023/08/11 00:00:00]) AS [DATE_2023-08-11],\n" +
            "         SUM([2023/08/12 00:00:00]) AS [DATE_2023-08-12]\n" +
            "FROM     QL_ITEMD_R PIVOT (MAX (QTY) FOR DATE IN ([1970/10/01 00:00:00], [2023/06/12 00:00:00], [2023/06/14 00:00:00], [2023/06/15 00:00:00], [2023/06/17 00:00:00], [2023/06/19 00:00:00], [2023/06/20 00:00:00], [2023/06/21 00:00:00], [2023/06/23 00:00:00], [2023/06/24 00:00:00], [2023/06/26 00:00:00], [2023/06/27 00:00:00], [2023/06/28 00:00:00], [2023/06/29 00:00:00], [2023/06/30 00:00:00], [2023/07/01 00:00:00], [2023/07/03 00:00:00], [2023/07/04 00:00:00], [2023/07/05 00:00:00], [2023/07/06 00:00:00], [2023/07/07 00:00:00], [2023/07/08 00:00:00], [2023/07/10 00:00:00], [2023/07/11 00:00:00], [2023/07/12 00:00:00], [2023/07/13 00:00:00], [2023/07/14 00:00:00], [2023/07/15 00:00:00], [2023/07/17 00:00:00], [2023/07/18 00:00:00], [2023/07/19 00:00:00], [2023/07/20 00:00:00], [2023/07/21 00:00:00], [2023/07/22 00:00:00], [2023/07/24 00:00:00], [2023/07/25 00:00:00], [2023/07/26 00:00:00], [2023/07/27 00:00:00], [2023/07/28 00:00:00], [2023/07/29 00:00:00], [2023/07/31 00:00:00], [2023/08/01 00:00:00], [2023/08/02 00:00:00], [2023/08/03 00:00:00], [2023/08/04 00:00:00], [2023/08/05 00:00:00], [2023/08/07 00:00:00], [2023/08/08 00:00:00], [2023/08/09 00:00:00], [2023/08/10 00:00:00], [2023/08/11 00:00:00], [2023/08/12 00:00:00])) AS MOR\n" +
            "WHERE    DPP_PROD_BATCH_RESULT_ID = '14682198-b51d-4501-acec-2674df28512f'\n" +
            "GROUP BY DOC_NO, SEQ, OPERATION_SEQ, OPERATION_CODE, SUB_ITEM_ID, SUB_ITEM_CODE, SUB_ITEM_FEATURE_ID, SUB_DICIMAL_DIGIT, ROW_TYPE, S_TYPE\n" +
            "ORDER BY SEQ, OPERATION_SEQ, OPERATION_CODE";
}
