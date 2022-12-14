/*********************************************************************************
* This file is part of KMADe Project.
* Copyright (C) 2006/2015  INRIA - MErLIn Project and LIAS/ISAE-ENSMA
* 
* KMADe is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* KMADe is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.
* 
* You should have received a copy of the GNU Lesser General Public License
* along with KMADe.  If not, see <http://www.gnu.org/licenses/>.
**********************************************************************************/
package fr.upensma.lias.kmade.tool.coreadaptator.parserexpression;

/** Token Manager. */
public class PreconditionTokenManager implements PreconditionConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x8181f0L) != 0L)
         {
            jjmatchedKind = 29;
            return 36;
         }
         return -1;
      case 1:
         if ((active0 & 0x8081f0L) != 0L)
         {
            jjmatchedKind = 31;
            jjmatchedPos = 1;
            return 37;
         }
         if ((active0 & 0x10000L) != 0L)
            return 37;
         return -1;
      case 2:
         if ((active0 & 0x808000L) != 0L)
            return 37;
         if ((active0 & 0x1f0L) != 0L)
         {
            jjmatchedKind = 31;
            jjmatchedPos = 2;
            return 37;
         }
         return -1;
      case 3:
         if ((active0 & 0x170L) != 0L)
         {
            jjmatchedKind = 31;
            jjmatchedPos = 3;
            return 37;
         }
         if ((active0 & 0x80L) != 0L)
            return 37;
         return -1;
      case 4:
         if ((active0 & 0x170L) != 0L)
         {
            jjmatchedKind = 31;
            jjmatchedPos = 4;
            return 37;
         }
         return -1;
      case 5:
         if ((active0 & 0x170L) != 0L)
         {
            jjmatchedKind = 31;
            jjmatchedPos = 5;
            return 37;
         }
         return -1;
      case 6:
         if ((active0 & 0x100L) != 0L)
         {
            if (jjmatchedPos != 6)
            {
               jjmatchedKind = 31;
               jjmatchedPos = 6;
            }
            return 37;
         }
         if ((active0 & 0x70L) != 0L)
            return 37;
         return -1;
      case 7:
         if ((active0 & 0x100L) != 0L)
            return 37;
         if ((active0 & 0x20L) != 0L)
         {
            jjmatchedKind = 31;
            jjmatchedPos = 7;
            return 37;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 10:
         return jjStopAtPos(0, 24);
      case 32:
         return jjStopAtPos(0, 1);
      case 33:
         return jjMoveStringLiteralDfa1_0(0x40000L);
      case 40:
         return jjStopAtPos(0, 12);
      case 41:
         return jjStopAtPos(0, 13);
      case 44:
         return jjStopAtPos(0, 14);
      case 60:
         jjmatchedKind = 19;
         return jjMoveStringLiteralDfa1_0(0x200000L);
      case 61:
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 62:
         jjmatchedKind = 20;
         return jjMoveStringLiteralDfa1_0(0x400000L);
      case 63:
         return jjMoveStringLiteralDfa1_0(0xe00L);
      case 65:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 79:
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x80L);
      case 103:
         return jjMoveStringLiteralDfa1_0(0x100L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x70L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x800000L);
      default :
         return jjMoveNfa_0(3, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 61:
         if ((active0 & 0x20000L) != 0L)
            return jjStopAtPos(1, 17);
         else if ((active0 & 0x40000L) != 0L)
            return jjStopAtPos(1, 18);
         else if ((active0 & 0x200000L) != 0L)
            return jjStopAtPos(1, 21);
         else if ((active0 & 0x400000L) != 0L)
            return jjStopAtPos(1, 22);
         break;
      case 66:
         return jjMoveStringLiteralDfa2_0(active0, 0x800L);
      case 78:
         return jjMoveStringLiteralDfa2_0(active0, 0x8200L);
      case 82:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(1, 16, 37);
         break;
      case 83:
         return jjMoveStringLiteralDfa2_0(active0, 0x400L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x80L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x100L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000L);
      case 115:
         return jjMoveStringLiteralDfa2_0(active0, 0x70L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 68:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(2, 15, 37);
         break;
      case 69:
         return jjMoveStringLiteralDfa3_0(active0, 0x70L);
      case 79:
         return jjMoveStringLiteralDfa3_0(active0, 0x800L);
      case 84:
         return jjMoveStringLiteralDfa3_0(active0, 0x400L);
      case 85:
         return jjMoveStringLiteralDfa3_0(active0, 0x200L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x80L);
      case 116:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(2, 23, 37);
         return jjMoveStringLiteralDfa3_0(active0, 0x100L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 77:
         if ((active0 & 0x200L) != 0L)
            return jjStopAtPos(3, 9);
         break;
      case 79:
         return jjMoveStringLiteralDfa4_0(active0, 0x800L);
      case 82:
         if ((active0 & 0x400L) != 0L)
            return jjStopAtPos(3, 10);
         break;
      case 86:
         return jjMoveStringLiteralDfa4_0(active0, 0x100L);
      case 100:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(3, 7, 37);
         break;
      case 109:
         return jjMoveStringLiteralDfa4_0(active0, 0x40L);
      case 120:
         return jjMoveStringLiteralDfa4_0(active0, 0x30L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 76:
         if ((active0 & 0x800L) != 0L)
            return jjStopAtPos(4, 11);
         break;
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x100L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x30L);
      case 112:
         return jjMoveStringLiteralDfa5_0(active0, 0x40L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 108:
         return jjMoveStringLiteralDfa6_0(active0, 0x100L);
      case 115:
         return jjMoveStringLiteralDfa6_0(active0, 0x30L);
      case 116:
         return jjMoveStringLiteralDfa6_0(active0, 0x40L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 116:
         if ((active0 & 0x10L) != 0L)
         {
            jjmatchedKind = 4;
            jjmatchedPos = 6;
         }
         return jjMoveStringLiteralDfa7_0(active0, 0x20L);
      case 117:
         return jjMoveStringLiteralDfa7_0(active0, 0x100L);
      case 121:
         if ((active0 & 0x40L) != 0L)
            return jjStartNfaWithStates_0(6, 6, 37);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa8_0(active0, 0x20L);
      case 101:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(7, 8, 37);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 116:
         if ((active0 & 0x20L) != 0L)
            return jjStartNfaWithStates_0(8, 5, 37);
         break;
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0x24008800000000L, 0x38100000000L
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 36;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 37:
                  if ((0x3ff642900000000L & l) != 0L)
                     jjCheckNAddTwoStates(31, 32);
                  if ((0x3ff642900000000L & l) != 0L)
                     jjCheckNAddTwoStates(29, 30);
                  if ((0x3ff642800000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStates(0, 2);
                  }
                  if ((0x3ff642800000000L & l) != 0L)
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAddStates(3, 5);
                  }
                  if ((0x3ff642800000000L & l) != 0L)
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAdd(33);
                  }
                  if ((0x3ff642800000000L & l) != 0L)
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAdd(32);
                  }
                  if ((0x3ff642800000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(30);
                  }
                  break;
               case 36:
                  if ((0x3ff642900000000L & l) != 0L)
                     jjCheckNAddTwoStates(31, 32);
                  if ((0x3ff642900000000L & l) != 0L)
                     jjCheckNAddTwoStates(29, 30);
                  if ((0x3ff642800000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStates(0, 2);
                  }
                  if ((0x3ff642800000000L & l) != 0L)
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAddStates(3, 5);
                  }
                  if ((0x3ff642800000000L & l) != 0L)
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAdd(33);
                  }
                  break;
               case 3:
                  if ((0x3ff642900000000L & l) != 0L)
                  {
                     if (kind > 30)
                        kind = 30;
                  }
                  else if (curChar == 39)
                     jjCheckNAddTwoStates(22, 23);
                  else if (curChar == 36)
                     jjstateSet[jjnewStateCnt++] = 16;
                  if ((0x3ff642800000000L & l) != 0L)
                  {
                     if (kind > 29)
                        kind = 29;
                     jjCheckNAddStates(6, 10);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 28)
                        kind = 28;
                     jjCheckNAddStates(11, 13);
                  }
                  else if (curChar == 45)
                     jjCheckNAddTwoStates(8, 9);
                  break;
               case 8:
                  if (curChar == 45)
                     jjCheckNAddTwoStates(8, 9);
                  break;
               case 9:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAddStates(11, 13);
                  break;
               case 10:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAdd(10);
                  break;
               case 11:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(11, 12);
                  break;
               case 12:
                  if (curChar != 46)
                     break;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAdd(13);
                  break;
               case 13:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAdd(13);
                  break;
               case 14:
                  if ((0x3ff642900000000L & l) != 0L && kind > 30)
                     kind = 30;
                  break;
               case 15:
                  if (curChar == 36)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 16:
                  if ((0x3ff642800000000L & l) == 0L)
                     break;
                  if (kind > 33)
                     kind = 33;
                  jjCheckNAddStates(14, 16);
                  break;
               case 17:
                  if ((0x3ff642900000000L & l) != 0L)
                     jjCheckNAddTwoStates(17, 18);
                  break;
               case 18:
                  if ((0x3ff642800000000L & l) == 0L)
                     break;
                  if (kind > 33)
                     kind = 33;
                  jjCheckNAdd(18);
                  break;
               case 19:
                  if ((0x3ff642800000000L & l) == 0L)
                     break;
                  if (kind > 33)
                     kind = 33;
                  jjCheckNAdd(19);
                  break;
               case 20:
                  if ((0x3ff642800000000L & l) == 0L)
                     break;
                  if (kind > 33)
                     kind = 33;
                  jjCheckNAddStates(17, 19);
                  break;
               case 21:
                  if (curChar == 39)
                     jjCheckNAddTwoStates(22, 23);
                  break;
               case 22:
                  if (curChar == 39 && kind > 34)
                     kind = 34;
                  break;
               case 23:
                  if ((0x3ff642800000000L & l) != 0L)
                     jjCheckNAddStates(20, 23);
                  break;
               case 24:
                  if ((0x3ff642900000000L & l) != 0L)
                     jjCheckNAddTwoStates(24, 25);
                  break;
               case 25:
                  if ((0x3ff642800000000L & l) != 0L)
                     jjCheckNAddTwoStates(22, 26);
                  break;
               case 26:
                  if ((0x3ff642800000000L & l) != 0L)
                     jjCheckNAddStates(24, 28);
                  break;
               case 27:
                  if ((0x3ff642800000000L & l) != 0L)
                     jjCheckNAddStates(29, 32);
                  break;
               case 28:
                  if ((0x3ff642800000000L & l) == 0L)
                     break;
                  if (kind > 29)
                     kind = 29;
                  jjCheckNAddStates(6, 10);
                  break;
               case 29:
                  if ((0x3ff642900000000L & l) != 0L)
                     jjCheckNAddTwoStates(29, 30);
                  break;
               case 30:
                  if ((0x3ff642800000000L & l) == 0L)
                     break;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAdd(30);
                  break;
               case 31:
                  if ((0x3ff642900000000L & l) != 0L)
                     jjCheckNAddTwoStates(31, 32);
                  break;
               case 32:
                  if ((0x3ff642800000000L & l) == 0L)
                     break;
                  if (kind > 32)
                     kind = 32;
                  jjCheckNAdd(32);
                  break;
               case 33:
                  if ((0x3ff642800000000L & l) == 0L)
                     break;
                  if (kind > 32)
                     kind = 32;
                  jjCheckNAdd(33);
                  break;
               case 34:
                  if ((0x3ff642800000000L & l) == 0L)
                     break;
                  if (kind > 32)
                     kind = 32;
                  jjCheckNAddStates(3, 5);
                  break;
               case 35:
                  if ((0x3ff642800000000L & l) == 0L)
                     break;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAddStates(0, 2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 37:
                  if ((0x7fffffff87ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStates(0, 2);
                  }
                  if ((0x7fffffff87ffffffL & l) != 0L)
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAddStates(3, 5);
                  }
                  if ((0x7fffffff87ffffffL & l) != 0L)
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAdd(33);
                  }
                  if ((0x7fffffff87ffffffL & l) != 0L)
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAdd(32);
                  }
                  if ((0x7fffffff87ffffffL & l) != 0L)
                     jjCheckNAddTwoStates(31, 32);
                  if ((0x7fffffff87ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(30);
                  }
                  if ((0x7fffffff87ffffffL & l) != 0L)
                     jjCheckNAddTwoStates(29, 30);
                  break;
               case 36:
                  if ((0x7fffffff87ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStates(0, 2);
                  }
                  if ((0x7fffffff87ffffffL & l) != 0L)
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAddStates(3, 5);
                  }
                  if ((0x7fffffff87ffffffL & l) != 0L)
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAdd(33);
                  }
                  if ((0x7fffffff87ffffffL & l) != 0L)
                     jjCheckNAddTwoStates(31, 32);
                  if ((0x7fffffff87ffffffL & l) != 0L)
                     jjCheckNAddTwoStates(29, 30);
                  break;
               case 3:
                  if ((0x7fffffff87ffffffL & l) != 0L)
                  {
                     if (kind > 29)
                        kind = 29;
                     jjCheckNAddStates(6, 10);
                  }
                  if ((0x7fffffff87ffffffL & l) != 0L)
                  {
                     if (kind > 30)
                        kind = 30;
                  }
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 6;
                  else if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 0:
                  if (curChar == 101 && kind > 27)
                     kind = 27;
                  break;
               case 1:
                  if (curChar == 117)
                     jjCheckNAdd(0);
                  break;
               case 2:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 4:
                  if (curChar == 115)
                     jjCheckNAdd(0);
                  break;
               case 5:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 6:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if (curChar == 102)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 14:
                  if ((0x7fffffff87ffffffL & l) != 0L && kind > 30)
                     kind = 30;
                  break;
               case 16:
                  if ((0x7fffffff87ffffffL & l) == 0L)
                     break;
                  if (kind > 33)
                     kind = 33;
                  jjCheckNAddStates(14, 16);
                  break;
               case 17:
                  if ((0x7fffffff87ffffffL & l) != 0L)
                     jjCheckNAddTwoStates(17, 18);
                  break;
               case 18:
                  if ((0x7fffffff87ffffffL & l) == 0L)
                     break;
                  if (kind > 33)
                     kind = 33;
                  jjCheckNAdd(18);
                  break;
               case 19:
                  if ((0x7fffffff87ffffffL & l) == 0L)
                     break;
                  if (kind > 33)
                     kind = 33;
                  jjCheckNAdd(19);
                  break;
               case 20:
                  if ((0x7fffffff87ffffffL & l) == 0L)
                     break;
                  if (kind > 33)
                     kind = 33;
                  jjCheckNAddStates(17, 19);
                  break;
               case 23:
                  if ((0x7fffffff87ffffffL & l) != 0L)
                     jjCheckNAddStates(20, 23);
                  break;
               case 24:
                  if ((0x7fffffff87ffffffL & l) != 0L)
                     jjCheckNAddTwoStates(24, 25);
                  break;
               case 25:
                  if ((0x7fffffff87ffffffL & l) != 0L)
                     jjCheckNAddTwoStates(22, 26);
                  break;
               case 26:
                  if ((0x7fffffff87ffffffL & l) != 0L)
                     jjCheckNAddStates(24, 28);
                  break;
               case 27:
                  if ((0x7fffffff87ffffffL & l) != 0L)
                     jjCheckNAddStates(29, 32);
                  break;
               case 28:
                  if ((0x7fffffff87ffffffL & l) == 0L)
                     break;
                  if (kind > 29)
                     kind = 29;
                  jjCheckNAddStates(6, 10);
                  break;
               case 29:
                  if ((0x7fffffff87ffffffL & l) != 0L)
                     jjCheckNAddTwoStates(29, 30);
                  break;
               case 30:
                  if ((0x7fffffff87ffffffL & l) == 0L)
                     break;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAdd(30);
                  break;
               case 31:
                  if ((0x7fffffff87ffffffL & l) != 0L)
                     jjCheckNAddTwoStates(31, 32);
                  break;
               case 32:
                  if ((0x7fffffff87ffffffL & l) == 0L)
                     break;
                  if (kind > 32)
                     kind = 32;
                  jjCheckNAdd(32);
                  break;
               case 33:
                  if ((0x7fffffff87ffffffL & l) == 0L)
                     break;
                  if (kind > 32)
                     kind = 32;
                  jjCheckNAdd(33);
                  break;
               case 34:
                  if ((0x7fffffff87ffffffL & l) == 0L)
                     break;
                  if (kind > 32)
                     kind = 32;
                  jjCheckNAddStates(3, 5);
                  break;
               case 35:
                  if ((0x7fffffff87ffffffL & l) == 0L)
                     break;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAddStates(0, 2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 37:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(29, 30);
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(30);
                  }
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(31, 32);
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAdd(32);
                  }
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAdd(33);
                  }
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAddStates(3, 5);
                  }
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStates(0, 2);
                  }
                  break;
               case 36:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(29, 30);
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(31, 32);
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAdd(33);
                  }
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 32)
                        kind = 32;
                     jjCheckNAddStates(3, 5);
                  }
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStates(0, 2);
                  }
                  break;
               case 3:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 30)
                        kind = 30;
                  }
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 29)
                        kind = 29;
                     jjCheckNAddStates(6, 10);
                  }
                  break;
               case 14:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 30)
                     kind = 30;
                  break;
               case 16:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 33)
                     kind = 33;
                  jjCheckNAddStates(14, 16);
                  break;
               case 17:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(17, 18);
                  break;
               case 18:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 33)
                     kind = 33;
                  jjCheckNAdd(18);
                  break;
               case 19:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 33)
                     kind = 33;
                  jjCheckNAdd(19);
                  break;
               case 20:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 33)
                     kind = 33;
                  jjCheckNAddStates(17, 19);
                  break;
               case 23:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddStates(20, 23);
                  break;
               case 24:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(24, 25);
                  break;
               case 25:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(22, 26);
                  break;
               case 26:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddStates(24, 28);
                  break;
               case 27:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddStates(29, 32);
                  break;
               case 28:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 29)
                     kind = 29;
                  jjCheckNAddStates(6, 10);
                  break;
               case 29:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(29, 30);
                  break;
               case 30:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAdd(30);
                  break;
               case 31:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(31, 32);
                  break;
               case 32:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 32)
                     kind = 32;
                  jjCheckNAdd(32);
                  break;
               case 33:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 32)
                     kind = 32;
                  jjCheckNAdd(33);
                  break;
               case 34:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 32)
                     kind = 32;
                  jjCheckNAddStates(3, 5);
                  break;
               case 35:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAddStates(0, 2);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 36 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   29, 30, 35, 31, 32, 34, 29, 31, 33, 34, 35, 10, 11, 12, 17, 19, 
   20, 17, 18, 20, 24, 22, 23, 27, 24, 22, 23, 26, 27, 24, 22, 26, 
   27, 
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec0[i2] & l2) != 0L);
      default :
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, "\151\163\105\170\151\163\164", 
"\151\163\105\170\151\163\164\101\164", "\151\163\105\155\160\164\171", "\143\141\162\144", 
"\147\145\164\126\141\154\165\145", "\77\116\125\115", "\77\123\124\122", "\77\102\117\117\114", "\50", "\51", 
"\54", "\101\116\104", "\117\122", "\75\75", "\41\75", "\74", "\76", "\74\75", 
"\76\75", "\156\157\164", "\12", null, null, null, null, null, null, null, null, null, 
null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x7f9fffff1L, 
};
static final long[] jjtoSkip = {
   0xeL, 
};
protected JavaCharStream input_stream;
private final int[] jjrounds = new int[36];
private final int[] jjstateSet = new int[72];
protected char curChar;
/** Constructor. */
public PreconditionTokenManager(JavaCharStream stream){
   if (JavaCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public PreconditionTokenManager(JavaCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(JavaCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 36; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(JavaCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 13 && (0x2200L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
@SuppressWarnings("unused")
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
