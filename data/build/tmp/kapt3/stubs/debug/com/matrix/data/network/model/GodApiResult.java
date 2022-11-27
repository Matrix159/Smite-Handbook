package com.matrix.data.network.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0003\b\u00b9\u0001\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u008d\u00022\u00020\u0001:\u0004\u008c\u0002\u008d\u0002B\u00af\u0005\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\u000b\u001a\u00020\f\u0012\b\b\u0001\u0010\r\u001a\u00020\f\u0012\b\b\u0001\u0010\u000e\u001a\u00020\f\u0012\b\b\u0001\u0010\u000f\u001a\u00020\f\u0012\b\b\u0001\u0010\u0010\u001a\u00020\f\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0012\u0012\b\b\u0001\u0010\u0017\u001a\u00020\u0018\u0012\b\b\u0001\u0010\u0019\u001a\u00020\u0018\u0012\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\u001c\u001a\u00020\u0018\u0012\b\b\u0001\u0010\u001d\u001a\u00020\f\u0012\b\b\u0001\u0010\u001e\u001a\u00020\u0018\u0012\b\b\u0001\u0010\u001f\u001a\u00020\u0018\u0012\n\b\u0001\u0010 \u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010!\u001a\u00020\u0018\u0012\b\b\u0001\u0010\"\u001a\u00020\u0018\u0012\b\b\u0001\u0010#\u001a\u00020\u0018\u0012\b\b\u0001\u0010$\u001a\u00020\f\u0012\b\b\u0001\u0010%\u001a\u00020\u0018\u0012\b\b\u0001\u0010&\u001a\u00020\f\u0012\b\b\u0001\u0010\'\u001a\u00020\u0018\u0012\b\b\u0001\u0010(\u001a\u00020\u0018\u0012\n\b\u0001\u0010)\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010*\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010+\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010,\u001a\u00020\f\u0012\b\b\u0001\u0010-\u001a\u00020\u0018\u0012\b\b\u0001\u0010.\u001a\u00020\u0018\u0012\b\b\u0001\u0010/\u001a\u00020\u0018\u0012\n\b\u0001\u00100\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u00101\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u00102\u001a\u00020\f\u0012\n\b\u0001\u00103\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u00104\u001a\u0004\u0018\u00010\u0006\u0012\b\u00105\u001a\u0004\u0018\u000106\u0012\b\u00107\u001a\u0004\u0018\u000106\u0012\b\u00108\u001a\u0004\u0018\u000106\u0012\b\u00109\u001a\u0004\u0018\u000106\u0012\b\u0010:\u001a\u0004\u0018\u000106\u0012\b\u0010;\u001a\u0004\u0018\u000106\u0012\n\b\u0001\u0010<\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010=\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010>\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010?\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010@\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010A\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010B\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010C\u001a\u00020\u0003\u0012\b\u0010D\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0001\u0010E\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010F\u001a\u0004\u0018\u00010G\u00a2\u0006\u0002\u0010HB\u00e7\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\f\u0012\u0006\u0010\u0010\u001a\u00020\f\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0012\u0012\u0006\u0010\u0016\u001a\u00020\u0012\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001a\u001a\u00020\u0006\u0012\u0006\u0010\u001b\u001a\u00020\u0006\u0012\u0006\u0010\u001c\u001a\u00020\u0018\u0012\u0006\u0010\u001d\u001a\u00020\f\u0012\u0006\u0010\u001e\u001a\u00020\u0018\u0012\u0006\u0010\u001f\u001a\u00020\u0018\u0012\u0006\u0010 \u001a\u00020\u0006\u0012\u0006\u0010!\u001a\u00020\u0018\u0012\u0006\u0010\"\u001a\u00020\u0018\u0012\u0006\u0010#\u001a\u00020\u0018\u0012\u0006\u0010$\u001a\u00020\f\u0012\u0006\u0010%\u001a\u00020\u0018\u0012\u0006\u0010&\u001a\u00020\f\u0012\u0006\u0010\'\u001a\u00020\u0018\u0012\u0006\u0010(\u001a\u00020\u0018\u0012\u0006\u0010)\u001a\u00020\u0006\u0012\u0006\u0010*\u001a\u00020\u0006\u0012\u0006\u0010+\u001a\u00020\u0006\u0012\u0006\u0010,\u001a\u00020\f\u0012\u0006\u0010-\u001a\u00020\u0018\u0012\u0006\u0010.\u001a\u00020\u0018\u0012\u0006\u0010/\u001a\u00020\u0018\u0012\u0006\u00100\u001a\u00020\u0006\u0012\u0006\u00101\u001a\u00020\u0006\u0012\u0006\u00102\u001a\u00020\f\u0012\u0006\u00103\u001a\u00020\u0006\u0012\u0006\u00104\u001a\u00020\u0006\u0012\u0006\u00105\u001a\u000206\u0012\u0006\u00107\u001a\u000206\u0012\u0006\u00108\u001a\u000206\u0012\u0006\u00109\u001a\u000206\u0012\u0006\u0010:\u001a\u000206\u0012\u0006\u0010;\u001a\u000206\u0012\u0006\u0010<\u001a\u00020\u0006\u0012\u0006\u0010=\u001a\u00020\u0006\u0012\u0006\u0010>\u001a\u00020\u0006\u0012\u0006\u0010?\u001a\u00020\u0006\u0012\u0006\u0010@\u001a\u00020\u0006\u0012\u0006\u0010A\u001a\u00020\u0006\u0012\u0006\u0010B\u001a\u00020\u0006\u0012\u0006\u0010C\u001a\u00020\u0003\u0012\u0006\u0010D\u001a\u00020\u0006\u0012\b\u0010E\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010IJ\n\u0010\u00c3\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00c4\u0001\u001a\u00020\fH\u00c6\u0003J\n\u0010\u00c5\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00c6\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00c7\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00c8\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00c9\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00ca\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00cb\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00cc\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00cd\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00ce\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00cf\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00d0\u0001\u001a\u00020\fH\u00c6\u0003J\n\u0010\u00d1\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00d2\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00d3\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00d4\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00d5\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00d6\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00d7\u0001\u001a\u00020\fH\u00c6\u0003J\n\u0010\u00d8\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00d9\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00da\u0001\u001a\u00020\fH\u00c6\u0003J\n\u0010\u00db\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00dc\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00dd\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00de\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00df\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00e0\u0001\u001a\u00020\fH\u00c6\u0003J\n\u0010\u00e1\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00e2\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00e3\u0001\u001a\u00020\u0018H\u00c6\u0003J\n\u0010\u00e4\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00e5\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00e6\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00e7\u0001\u001a\u00020\fH\u00c6\u0003J\n\u0010\u00e8\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00e9\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00ea\u0001\u001a\u000206H\u00c6\u0003J\n\u0010\u00eb\u0001\u001a\u000206H\u00c6\u0003J\n\u0010\u00ec\u0001\u001a\u000206H\u00c6\u0003J\n\u0010\u00ed\u0001\u001a\u000206H\u00c6\u0003J\n\u0010\u00ee\u0001\u001a\u000206H\u00c6\u0003J\n\u0010\u00ef\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00f0\u0001\u001a\u000206H\u00c6\u0003J\n\u0010\u00f1\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00f2\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00f3\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00f4\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00f5\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00f6\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00f7\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00f8\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00f9\u0001\u001a\u00020\u0006H\u00c6\u0003J\n\u0010\u00fa\u0001\u001a\u00020\fH\u00c6\u0003J\f\u0010\u00fb\u0001\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\n\u0010\u00fc\u0001\u001a\u00020\fH\u00c6\u0003J\n\u0010\u00fd\u0001\u001a\u00020\fH\u00c6\u0003J\n\u0010\u00fe\u0001\u001a\u00020\fH\u00c6\u0003J\u00e4\u0004\u0010\u00ff\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u00182\b\b\u0002\u0010\u001d\u001a\u00020\f2\b\b\u0002\u0010\u001e\u001a\u00020\u00182\b\b\u0002\u0010\u001f\u001a\u00020\u00182\b\b\u0002\u0010 \u001a\u00020\u00062\b\b\u0002\u0010!\u001a\u00020\u00182\b\b\u0002\u0010\"\u001a\u00020\u00182\b\b\u0002\u0010#\u001a\u00020\u00182\b\b\u0002\u0010$\u001a\u00020\f2\b\b\u0002\u0010%\u001a\u00020\u00182\b\b\u0002\u0010&\u001a\u00020\f2\b\b\u0002\u0010\'\u001a\u00020\u00182\b\b\u0002\u0010(\u001a\u00020\u00182\b\b\u0002\u0010)\u001a\u00020\u00062\b\b\u0002\u0010*\u001a\u00020\u00062\b\b\u0002\u0010+\u001a\u00020\u00062\b\b\u0002\u0010,\u001a\u00020\f2\b\b\u0002\u0010-\u001a\u00020\u00182\b\b\u0002\u0010.\u001a\u00020\u00182\b\b\u0002\u0010/\u001a\u00020\u00182\b\b\u0002\u00100\u001a\u00020\u00062\b\b\u0002\u00101\u001a\u00020\u00062\b\b\u0002\u00102\u001a\u00020\f2\b\b\u0002\u00103\u001a\u00020\u00062\b\b\u0002\u00104\u001a\u00020\u00062\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u0002062\b\b\u0002\u00108\u001a\u0002062\b\b\u0002\u00109\u001a\u0002062\b\b\u0002\u0010:\u001a\u0002062\b\b\u0002\u0010;\u001a\u0002062\b\b\u0002\u0010<\u001a\u00020\u00062\b\b\u0002\u0010=\u001a\u00020\u00062\b\b\u0002\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u00062\b\b\u0002\u0010@\u001a\u00020\u00062\b\b\u0002\u0010A\u001a\u00020\u00062\b\b\u0002\u0010B\u001a\u00020\u00062\b\b\u0002\u0010C\u001a\u00020\u00032\b\b\u0002\u0010D\u001a\u00020\u00062\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001J\u0016\u0010\u0080\u0002\u001a\u00030\u0081\u00022\t\u0010\u0082\u0002\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\n\u0010\u0083\u0002\u001a\u00020\u0003H\u00d6\u0001J\n\u0010\u0084\u0002\u001a\u00020\u0006H\u00d6\u0001J(\u0010\u0085\u0002\u001a\u00030\u0086\u00022\u0007\u0010\u0087\u0002\u001a\u00020\u00002\b\u0010\u0088\u0002\u001a\u00030\u0089\u00022\b\u0010\u008a\u0002\u001a\u00030\u008b\u0002H\u00c7\u0001R\u001c\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bJ\u0010K\u001a\u0004\bL\u0010MR\u001c\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bN\u0010K\u001a\u0004\bO\u0010MR\u001c\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bP\u0010K\u001a\u0004\bQ\u0010MR\u001c\u0010\t\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bR\u0010K\u001a\u0004\bS\u0010MR\u001c\u0010\n\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bT\u0010K\u001a\u0004\bU\u0010MR\u0011\u00105\u001a\u000206\u00a2\u0006\b\n\u0000\u001a\u0004\bV\u0010WR\u0011\u00107\u001a\u000206\u00a2\u0006\b\n\u0000\u001a\u0004\bX\u0010WR\u0011\u00108\u001a\u000206\u00a2\u0006\b\n\u0000\u001a\u0004\bY\u0010WR\u0011\u00109\u001a\u000206\u00a2\u0006\b\n\u0000\u001a\u0004\bZ\u0010WR\u0011\u0010:\u001a\u000206\u00a2\u0006\b\n\u0000\u001a\u0004\b[\u0010WR\u001c\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b\\\u0010K\u001a\u0004\b]\u0010^R\u001c\u0010\u0013\u001a\u00020\u00128\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b_\u0010K\u001a\u0004\b`\u0010^R\u001c\u0010\u0014\u001a\u00020\u00128\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\ba\u0010K\u001a\u0004\bb\u0010^R\u001c\u0010\u0015\u001a\u00020\u00128\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bc\u0010K\u001a\u0004\bd\u0010^R\u001c\u0010\u0016\u001a\u00020\u00128\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\be\u0010K\u001a\u0004\bf\u0010^R\u001c\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bg\u0010K\u001a\u0004\bh\u0010iR\u001c\u0010\r\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bj\u0010K\u001a\u0004\bk\u0010iR\u001c\u0010\u000e\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bl\u0010K\u001a\u0004\bm\u0010iR\u001c\u0010\u000f\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bn\u0010K\u001a\u0004\bo\u0010iR\u001c\u0010\u0010\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bp\u0010K\u001a\u0004\bq\u0010iR\u001c\u0010\u0017\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\br\u0010K\u001a\u0004\bs\u0010tR\u001c\u0010\u0019\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bu\u0010K\u001a\u0004\bv\u0010tR\u001c\u0010\u001a\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bw\u0010K\u001a\u0004\bx\u0010MR\u0011\u0010;\u001a\u000206\u00a2\u0006\b\n\u0000\u001a\u0004\by\u0010WR\u001c\u0010\u001b\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\bz\u0010K\u001a\u0004\b{\u0010MR\u001c\u0010<\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b|\u0010K\u001a\u0004\b}\u0010MR\u001c\u0010=\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u000e\n\u0000\u0012\u0004\b~\u0010K\u001a\u0004\b\u007f\u0010MR\u001e\u0010>\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u0080\u0001\u0010K\u001a\u0005\b\u0081\u0001\u0010MR\u001e\u0010?\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u0082\u0001\u0010K\u001a\u0005\b\u0083\u0001\u0010MR\u001e\u0010@\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u0084\u0001\u0010K\u001a\u0005\b\u0085\u0001\u0010MR(\u0010A\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0018\n\u0000\u0012\u0005\b\u0086\u0001\u0010K\u001a\u0005\b\u0087\u0001\u0010M\"\u0006\b\u0088\u0001\u0010\u0089\u0001R\u001e\u0010B\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u008a\u0001\u0010K\u001a\u0005\b\u008b\u0001\u0010MR\u001e\u0010\u001d\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u008c\u0001\u0010K\u001a\u0005\b\u008d\u0001\u0010iR\u001e\u0010\u001e\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u008e\u0001\u0010K\u001a\u0005\b\u008f\u0001\u0010tR\u001e\u0010\u001f\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u0090\u0001\u0010K\u001a\u0005\b\u0091\u0001\u0010tR\u001e\u0010\u001c\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u0092\u0001\u0010K\u001a\u0005\b\u0093\u0001\u0010tR\u0013\u0010C\u001a\u00020\u0003\u00a2\u0006\n\n\u0000\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R\u0012\u0010D\u001a\u00020\u0006\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0096\u0001\u0010MR\u001e\u0010 \u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u0097\u0001\u0010K\u001a\u0005\b\u0098\u0001\u0010MR\u001e\u0010\"\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u0099\u0001\u0010K\u001a\u0005\b\u009a\u0001\u0010tR\u001e\u0010#\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u009b\u0001\u0010K\u001a\u0005\b\u009c\u0001\u0010tR\u001e\u0010$\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u009d\u0001\u0010K\u001a\u0005\b\u009e\u0001\u0010iR\u001e\u0010%\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u009f\u0001\u0010K\u001a\u0005\b\u00a0\u0001\u0010tR\u001e\u0010&\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00a1\u0001\u0010K\u001a\u0005\b\u00a2\u0001\u0010iR\u001e\u0010\'\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00a3\u0001\u0010K\u001a\u0005\b\u00a4\u0001\u0010tR\u001e\u0010(\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00a5\u0001\u0010K\u001a\u0005\b\u00a6\u0001\u0010tR\u001e\u0010!\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00a7\u0001\u0010K\u001a\u0005\b\u00a8\u0001\u0010tR\u001e\u0010)\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00a9\u0001\u0010K\u001a\u0005\b\u00aa\u0001\u0010MR\u001e\u0010*\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00ab\u0001\u0010K\u001a\u0005\b\u00ac\u0001\u0010MR\u001e\u0010+\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00ad\u0001\u0010K\u001a\u0005\b\u00ae\u0001\u0010MR\u001e\u0010,\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00af\u0001\u0010K\u001a\u0005\b\u00b0\u0001\u0010iR\u001e\u0010-\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00b1\u0001\u0010K\u001a\u0005\b\u00b2\u0001\u0010tR\u001e\u0010.\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00b3\u0001\u0010K\u001a\u0005\b\u00b4\u0001\u0010tR\u001e\u0010/\u001a\u00020\u00188\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00b5\u0001\u0010K\u001a\u0005\b\u00b6\u0001\u0010tR\u001e\u00100\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00b7\u0001\u0010K\u001a\u0005\b\u00b8\u0001\u0010MR \u0010E\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00b9\u0001\u0010K\u001a\u0005\b\u00ba\u0001\u0010MR\u001e\u00101\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00bb\u0001\u0010K\u001a\u0005\b\u00bc\u0001\u0010MR\u001e\u00102\u001a\u00020\f8\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00bd\u0001\u0010K\u001a\u0005\b\u00be\u0001\u0010iR\u001e\u00103\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00bf\u0001\u0010K\u001a\u0005\b\u00c0\u0001\u0010MR\u001e\u00104\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\u0010\n\u0000\u0012\u0005\b\u00c1\u0001\u0010K\u001a\u0005\b\u00c2\u0001\u0010M\u00a8\u0006\u008e\u0002"}, d2 = {"Lcom/matrix/data/network/model/GodApiResult;", "", "seen1", "", "seen2", "ability1", "", "ability2", "ability3", "ability4", "ability5", "abilityId1", "", "abilityId2", "abilityId3", "abilityId4", "abilityId5", "abilityDetails1", "Lcom/matrix/data/network/model/Ability;", "abilityDetails2", "abilityDetails3", "abilityDetails4", "abilityDetails5", "attackSpeed", "", "attackSpeedPerLevel", "autoBanned", "cons", "hp5PerLevel", "health", "healthPerFive", "healthPerLevel", "lore", "mp5PerLevel", "magicProtection", "magicProtectionPerLevel", "magicalPower", "magicalPowerPerLevel", "mana", "manaPerFive", "manaPerLevel", "name", "onFreeRotation", "pantheon", "physicalPower", "physicalPowerPerLevel", "physicalProtection", "physicalProtectionPerLevel", "pros", "roles", "speed", "title", "type", "abilityDescription1", "Lcom/matrix/data/network/model/AbilityDescription;", "abilityDescription2", "abilityDescription3", "abilityDescription4", "abilityDescription5", "basicAttack", "godAbility1URL", "godAbility2URL", "godAbility3URL", "godAbility4URL", "godAbility5URL", "godCardURL", "godIconURL", "id", "latestGod", "retMsg", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJJJLcom/matrix/data/network/model/Ability;Lcom/matrix/data/network/model/Ability;Lcom/matrix/data/network/model/Ability;Lcom/matrix/data/network/model/Ability;Lcom/matrix/data/network/model/Ability;DDLjava/lang/String;Ljava/lang/String;DJDDLjava/lang/String;DDDJDJDDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JDDDLjava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Lcom/matrix/data/network/model/AbilityDescription;Lcom/matrix/data/network/model/AbilityDescription;Lcom/matrix/data/network/model/AbilityDescription;Lcom/matrix/data/network/model/AbilityDescription;Lcom/matrix/data/network/model/AbilityDescription;Lcom/matrix/data/network/model/AbilityDescription;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJJJLcom/matrix/data/network/model/Ability;Lcom/matrix/data/network/model/Ability;Lcom/matrix/data/network/model/Ability;Lcom/matrix/data/network/model/Ability;Lcom/matrix/data/network/model/Ability;DDLjava/lang/String;Ljava/lang/String;DJDDLjava/lang/String;DDDJDJDDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JDDDLjava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Lcom/matrix/data/network/model/AbilityDescription;Lcom/matrix/data/network/model/AbilityDescription;Lcom/matrix/data/network/model/AbilityDescription;Lcom/matrix/data/network/model/AbilityDescription;Lcom/matrix/data/network/model/AbilityDescription;Lcom/matrix/data/network/model/AbilityDescription;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getAbility1$annotations", "()V", "getAbility1", "()Ljava/lang/String;", "getAbility2$annotations", "getAbility2", "getAbility3$annotations", "getAbility3", "getAbility4$annotations", "getAbility4", "getAbility5$annotations", "getAbility5", "getAbilityDescription1", "()Lcom/matrix/data/network/model/AbilityDescription;", "getAbilityDescription2", "getAbilityDescription3", "getAbilityDescription4", "getAbilityDescription5", "getAbilityDetails1$annotations", "getAbilityDetails1", "()Lcom/matrix/data/network/model/Ability;", "getAbilityDetails2$annotations", "getAbilityDetails2", "getAbilityDetails3$annotations", "getAbilityDetails3", "getAbilityDetails4$annotations", "getAbilityDetails4", "getAbilityDetails5$annotations", "getAbilityDetails5", "getAbilityId1$annotations", "getAbilityId1", "()J", "getAbilityId2$annotations", "getAbilityId2", "getAbilityId3$annotations", "getAbilityId3", "getAbilityId4$annotations", "getAbilityId4", "getAbilityId5$annotations", "getAbilityId5", "getAttackSpeed$annotations", "getAttackSpeed", "()D", "getAttackSpeedPerLevel$annotations", "getAttackSpeedPerLevel", "getAutoBanned$annotations", "getAutoBanned", "getBasicAttack", "getCons$annotations", "getCons", "getGodAbility1URL$annotations", "getGodAbility1URL", "getGodAbility2URL$annotations", "getGodAbility2URL", "getGodAbility3URL$annotations", "getGodAbility3URL", "getGodAbility4URL$annotations", "getGodAbility4URL", "getGodAbility5URL$annotations", "getGodAbility5URL", "getGodCardURL$annotations", "getGodCardURL", "setGodCardURL", "(Ljava/lang/String;)V", "getGodIconURL$annotations", "getGodIconURL", "getHealth$annotations", "getHealth", "getHealthPerFive$annotations", "getHealthPerFive", "getHealthPerLevel$annotations", "getHealthPerLevel", "getHp5PerLevel$annotations", "getHp5PerLevel", "getId", "()I", "getLatestGod", "getLore$annotations", "getLore", "getMagicProtection$annotations", "getMagicProtection", "getMagicProtectionPerLevel$annotations", "getMagicProtectionPerLevel", "getMagicalPower$annotations", "getMagicalPower", "getMagicalPowerPerLevel$annotations", "getMagicalPowerPerLevel", "getMana$annotations", "getMana", "getManaPerFive$annotations", "getManaPerFive", "getManaPerLevel$annotations", "getManaPerLevel", "getMp5PerLevel$annotations", "getMp5PerLevel", "getName$annotations", "getName", "getOnFreeRotation$annotations", "getOnFreeRotation", "getPantheon$annotations", "getPantheon", "getPhysicalPower$annotations", "getPhysicalPower", "getPhysicalPowerPerLevel$annotations", "getPhysicalPowerPerLevel", "getPhysicalProtection$annotations", "getPhysicalProtection", "getPhysicalProtectionPerLevel$annotations", "getPhysicalProtectionPerLevel", "getPros$annotations", "getPros", "getRetMsg$annotations", "getRetMsg", "getRoles$annotations", "getRoles", "getSpeed$annotations", "getSpeed", "getTitle$annotations", "getTitle", "getType$annotations", "getType", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component50", "component51", "component52", "component53", "component54", "component55", "component56", "component57", "component58", "component59", "component6", "component60", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "data_debug"})
@kotlinx.serialization.Serializable
public final class GodApiResult {
    @org.jetbrains.annotations.NotNull
    public static final com.matrix.data.network.model.GodApiResult.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String ability1 = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String ability2 = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String ability3 = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String ability4 = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String ability5 = null;
    private final long abilityId1 = 0L;
    private final long abilityId2 = 0L;
    private final long abilityId3 = 0L;
    private final long abilityId4 = 0L;
    private final long abilityId5 = 0L;
    @org.jetbrains.annotations.NotNull
    private final com.matrix.data.network.model.Ability abilityDetails1 = null;
    @org.jetbrains.annotations.NotNull
    private final com.matrix.data.network.model.Ability abilityDetails2 = null;
    @org.jetbrains.annotations.NotNull
    private final com.matrix.data.network.model.Ability abilityDetails3 = null;
    @org.jetbrains.annotations.NotNull
    private final com.matrix.data.network.model.Ability abilityDetails4 = null;
    @org.jetbrains.annotations.NotNull
    private final com.matrix.data.network.model.Ability abilityDetails5 = null;
    private final double attackSpeed = 0.0;
    private final double attackSpeedPerLevel = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String autoBanned = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String cons = null;
    private final double hp5PerLevel = 0.0;
    private final long health = 0L;
    private final double healthPerFive = 0.0;
    private final double healthPerLevel = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String lore = null;
    private final double mp5PerLevel = 0.0;
    private final double magicProtection = 0.0;
    private final double magicProtectionPerLevel = 0.0;
    private final long magicalPower = 0L;
    private final double magicalPowerPerLevel = 0.0;
    private final long mana = 0L;
    private final double manaPerFive = 0.0;
    private final double manaPerLevel = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String onFreeRotation = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String pantheon = null;
    private final long physicalPower = 0L;
    private final double physicalPowerPerLevel = 0.0;
    private final double physicalProtection = 0.0;
    private final double physicalProtectionPerLevel = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String pros = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String roles = null;
    private final long speed = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String title = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String type = null;
    @org.jetbrains.annotations.NotNull
    private final com.matrix.data.network.model.AbilityDescription abilityDescription1 = null;
    @org.jetbrains.annotations.NotNull
    private final com.matrix.data.network.model.AbilityDescription abilityDescription2 = null;
    @org.jetbrains.annotations.NotNull
    private final com.matrix.data.network.model.AbilityDescription abilityDescription3 = null;
    @org.jetbrains.annotations.NotNull
    private final com.matrix.data.network.model.AbilityDescription abilityDescription4 = null;
    @org.jetbrains.annotations.NotNull
    private final com.matrix.data.network.model.AbilityDescription abilityDescription5 = null;
    @org.jetbrains.annotations.NotNull
    private final com.matrix.data.network.model.AbilityDescription basicAttack = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String godAbility1URL = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String godAbility2URL = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String godAbility3URL = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String godAbility4URL = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String godAbility5URL = null;
    @org.jetbrains.annotations.NotNull
    private java.lang.String godCardURL;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String godIconURL = null;
    private final int id = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String latestGod = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String retMsg = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.GodApiResult copy(@org.jetbrains.annotations.NotNull
    java.lang.String ability1, @org.jetbrains.annotations.NotNull
    java.lang.String ability2, @org.jetbrains.annotations.NotNull
    java.lang.String ability3, @org.jetbrains.annotations.NotNull
    java.lang.String ability4, @org.jetbrains.annotations.NotNull
    java.lang.String ability5, long abilityId1, long abilityId2, long abilityId3, long abilityId4, long abilityId5, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.Ability abilityDetails1, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.Ability abilityDetails2, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.Ability abilityDetails3, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.Ability abilityDetails4, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.Ability abilityDetails5, double attackSpeed, double attackSpeedPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String autoBanned, @org.jetbrains.annotations.NotNull
    java.lang.String cons, double hp5PerLevel, long health, double healthPerFive, double healthPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String lore, double mp5PerLevel, double magicProtection, double magicProtectionPerLevel, long magicalPower, double magicalPowerPerLevel, long mana, double manaPerFive, double manaPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String onFreeRotation, @org.jetbrains.annotations.NotNull
    java.lang.String pantheon, long physicalPower, double physicalPowerPerLevel, double physicalProtection, double physicalProtectionPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String pros, @org.jetbrains.annotations.NotNull
    java.lang.String roles, long speed, @org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.AbilityDescription abilityDescription1, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.AbilityDescription abilityDescription2, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.AbilityDescription abilityDescription3, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.AbilityDescription abilityDescription4, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.AbilityDescription abilityDescription5, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.AbilityDescription basicAttack, @org.jetbrains.annotations.NotNull
    java.lang.String godAbility1URL, @org.jetbrains.annotations.NotNull
    java.lang.String godAbility2URL, @org.jetbrains.annotations.NotNull
    java.lang.String godAbility3URL, @org.jetbrains.annotations.NotNull
    java.lang.String godAbility4URL, @org.jetbrains.annotations.NotNull
    java.lang.String godAbility5URL, @org.jetbrains.annotations.NotNull
    java.lang.String godCardURL, @org.jetbrains.annotations.NotNull
    java.lang.String godIconURL, int id, @org.jetbrains.annotations.NotNull
    java.lang.String latestGod, @org.jetbrains.annotations.Nullable
    java.lang.String retMsg) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.jvm.JvmStatic
    public static final void write$Self(@org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.GodApiResult self, @org.jetbrains.annotations.NotNull
    kotlinx.serialization.encoding.CompositeEncoder output, @org.jetbrains.annotations.NotNull
    kotlinx.serialization.descriptors.SerialDescriptor serialDesc) {
    }
    
    public GodApiResult(@org.jetbrains.annotations.NotNull
    java.lang.String ability1, @org.jetbrains.annotations.NotNull
    java.lang.String ability2, @org.jetbrains.annotations.NotNull
    java.lang.String ability3, @org.jetbrains.annotations.NotNull
    java.lang.String ability4, @org.jetbrains.annotations.NotNull
    java.lang.String ability5, long abilityId1, long abilityId2, long abilityId3, long abilityId4, long abilityId5, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.Ability abilityDetails1, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.Ability abilityDetails2, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.Ability abilityDetails3, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.Ability abilityDetails4, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.Ability abilityDetails5, double attackSpeed, double attackSpeedPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String autoBanned, @org.jetbrains.annotations.NotNull
    java.lang.String cons, double hp5PerLevel, long health, double healthPerFive, double healthPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String lore, double mp5PerLevel, double magicProtection, double magicProtectionPerLevel, long magicalPower, double magicalPowerPerLevel, long mana, double manaPerFive, double manaPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String onFreeRotation, @org.jetbrains.annotations.NotNull
    java.lang.String pantheon, long physicalPower, double physicalPowerPerLevel, double physicalProtection, double physicalProtectionPerLevel, @org.jetbrains.annotations.NotNull
    java.lang.String pros, @org.jetbrains.annotations.NotNull
    java.lang.String roles, long speed, @org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.AbilityDescription abilityDescription1, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.AbilityDescription abilityDescription2, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.AbilityDescription abilityDescription3, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.AbilityDescription abilityDescription4, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.AbilityDescription abilityDescription5, @org.jetbrains.annotations.NotNull
    com.matrix.data.network.model.AbilityDescription basicAttack, @org.jetbrains.annotations.NotNull
    java.lang.String godAbility1URL, @org.jetbrains.annotations.NotNull
    java.lang.String godAbility2URL, @org.jetbrains.annotations.NotNull
    java.lang.String godAbility3URL, @org.jetbrains.annotations.NotNull
    java.lang.String godAbility4URL, @org.jetbrains.annotations.NotNull
    java.lang.String godAbility5URL, @org.jetbrains.annotations.NotNull
    java.lang.String godCardURL, @org.jetbrains.annotations.NotNull
    java.lang.String godIconURL, int id, @org.jetbrains.annotations.NotNull
    java.lang.String latestGod, @org.jetbrains.annotations.Nullable
    java.lang.String retMsg) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAbility1() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Ability1")
    @java.lang.Deprecated
    public static void getAbility1$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAbility2() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Ability2")
    @java.lang.Deprecated
    public static void getAbility2$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAbility3() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Ability3")
    @java.lang.Deprecated
    public static void getAbility3$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAbility4() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Ability4")
    @java.lang.Deprecated
    public static void getAbility4$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAbility5() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Ability5")
    @java.lang.Deprecated
    public static void getAbility5$annotations() {
    }
    
    public final long component6() {
        return 0L;
    }
    
    public final long getAbilityId1() {
        return 0L;
    }
    
    @kotlinx.serialization.SerialName(value = "AbilityId1")
    @java.lang.Deprecated
    public static void getAbilityId1$annotations() {
    }
    
    public final long component7() {
        return 0L;
    }
    
    public final long getAbilityId2() {
        return 0L;
    }
    
    @kotlinx.serialization.SerialName(value = "AbilityId2")
    @java.lang.Deprecated
    public static void getAbilityId2$annotations() {
    }
    
    public final long component8() {
        return 0L;
    }
    
    public final long getAbilityId3() {
        return 0L;
    }
    
    @kotlinx.serialization.SerialName(value = "AbilityId3")
    @java.lang.Deprecated
    public static void getAbilityId3$annotations() {
    }
    
    public final long component9() {
        return 0L;
    }
    
    public final long getAbilityId4() {
        return 0L;
    }
    
    @kotlinx.serialization.SerialName(value = "AbilityId4")
    @java.lang.Deprecated
    public static void getAbilityId4$annotations() {
    }
    
    public final long component10() {
        return 0L;
    }
    
    public final long getAbilityId5() {
        return 0L;
    }
    
    @kotlinx.serialization.SerialName(value = "AbilityId5")
    @java.lang.Deprecated
    public static void getAbilityId5$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.Ability component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.Ability getAbilityDetails1() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Ability_1")
    @java.lang.Deprecated
    public static void getAbilityDetails1$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.Ability component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.Ability getAbilityDetails2() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Ability_2")
    @java.lang.Deprecated
    public static void getAbilityDetails2$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.Ability component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.Ability getAbilityDetails3() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Ability_3")
    @java.lang.Deprecated
    public static void getAbilityDetails3$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.Ability component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.Ability getAbilityDetails4() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Ability_4")
    @java.lang.Deprecated
    public static void getAbilityDetails4$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.Ability component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.Ability getAbilityDetails5() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Ability_5")
    @java.lang.Deprecated
    public static void getAbilityDetails5$annotations() {
    }
    
    public final double component16() {
        return 0.0;
    }
    
    public final double getAttackSpeed() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "AttackSpeed")
    @java.lang.Deprecated
    public static void getAttackSpeed$annotations() {
    }
    
    public final double component17() {
        return 0.0;
    }
    
    public final double getAttackSpeedPerLevel() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "AttackSpeedPerLevel")
    @java.lang.Deprecated
    public static void getAttackSpeedPerLevel$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component18() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAutoBanned() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "AutoBanned")
    @java.lang.Deprecated
    public static void getAutoBanned$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCons() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Cons")
    @java.lang.Deprecated
    public static void getCons$annotations() {
    }
    
    public final double component20() {
        return 0.0;
    }
    
    public final double getHp5PerLevel() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "HP5PerLevel")
    @java.lang.Deprecated
    public static void getHp5PerLevel$annotations() {
    }
    
    public final long component21() {
        return 0L;
    }
    
    public final long getHealth() {
        return 0L;
    }
    
    @kotlinx.serialization.SerialName(value = "Health")
    @java.lang.Deprecated
    public static void getHealth$annotations() {
    }
    
    public final double component22() {
        return 0.0;
    }
    
    public final double getHealthPerFive() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "HealthPerFive")
    @java.lang.Deprecated
    public static void getHealthPerFive$annotations() {
    }
    
    public final double component23() {
        return 0.0;
    }
    
    public final double getHealthPerLevel() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "HealthPerLevel")
    @java.lang.Deprecated
    public static void getHealthPerLevel$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLore() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Lore")
    @java.lang.Deprecated
    public static void getLore$annotations() {
    }
    
    public final double component25() {
        return 0.0;
    }
    
    public final double getMp5PerLevel() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "MP5PerLevel")
    @java.lang.Deprecated
    public static void getMp5PerLevel$annotations() {
    }
    
    public final double component26() {
        return 0.0;
    }
    
    public final double getMagicProtection() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "MagicProtection")
    @java.lang.Deprecated
    public static void getMagicProtection$annotations() {
    }
    
    public final double component27() {
        return 0.0;
    }
    
    public final double getMagicProtectionPerLevel() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "MagicProtectionPerLevel")
    @java.lang.Deprecated
    public static void getMagicProtectionPerLevel$annotations() {
    }
    
    public final long component28() {
        return 0L;
    }
    
    public final long getMagicalPower() {
        return 0L;
    }
    
    @kotlinx.serialization.SerialName(value = "MagicalPower")
    @java.lang.Deprecated
    public static void getMagicalPower$annotations() {
    }
    
    public final double component29() {
        return 0.0;
    }
    
    public final double getMagicalPowerPerLevel() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "MagicalPowerPerLevel")
    @java.lang.Deprecated
    public static void getMagicalPowerPerLevel$annotations() {
    }
    
    public final long component30() {
        return 0L;
    }
    
    public final long getMana() {
        return 0L;
    }
    
    @kotlinx.serialization.SerialName(value = "Mana")
    @java.lang.Deprecated
    public static void getMana$annotations() {
    }
    
    public final double component31() {
        return 0.0;
    }
    
    public final double getManaPerFive() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "ManaPerFive")
    @java.lang.Deprecated
    public static void getManaPerFive$annotations() {
    }
    
    public final double component32() {
        return 0.0;
    }
    
    public final double getManaPerLevel() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "ManaPerLevel")
    @java.lang.Deprecated
    public static void getManaPerLevel$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component33() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Name")
    @java.lang.Deprecated
    public static void getName$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component34() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getOnFreeRotation() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "OnFreeRotation")
    @java.lang.Deprecated
    public static void getOnFreeRotation$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component35() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPantheon() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Pantheon")
    @java.lang.Deprecated
    public static void getPantheon$annotations() {
    }
    
    public final long component36() {
        return 0L;
    }
    
    public final long getPhysicalPower() {
        return 0L;
    }
    
    @kotlinx.serialization.SerialName(value = "PhysicalPower")
    @java.lang.Deprecated
    public static void getPhysicalPower$annotations() {
    }
    
    public final double component37() {
        return 0.0;
    }
    
    public final double getPhysicalPowerPerLevel() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "PhysicalPowerPerLevel")
    @java.lang.Deprecated
    public static void getPhysicalPowerPerLevel$annotations() {
    }
    
    public final double component38() {
        return 0.0;
    }
    
    public final double getPhysicalProtection() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "PhysicalProtection")
    @java.lang.Deprecated
    public static void getPhysicalProtection$annotations() {
    }
    
    public final double component39() {
        return 0.0;
    }
    
    public final double getPhysicalProtectionPerLevel() {
        return 0.0;
    }
    
    @kotlinx.serialization.SerialName(value = "PhysicalProtectionPerLevel")
    @java.lang.Deprecated
    public static void getPhysicalProtectionPerLevel$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component40() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPros() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Pros")
    @java.lang.Deprecated
    public static void getPros$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component41() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRoles() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Roles")
    @java.lang.Deprecated
    public static void getRoles$annotations() {
    }
    
    public final long component42() {
        return 0L;
    }
    
    public final long getSpeed() {
        return 0L;
    }
    
    @kotlinx.serialization.SerialName(value = "Speed")
    @java.lang.Deprecated
    public static void getSpeed$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component43() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTitle() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Title")
    @java.lang.Deprecated
    public static void getTitle$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component44() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getType() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "Type")
    @java.lang.Deprecated
    public static void getType$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.AbilityDescription component45() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.AbilityDescription getAbilityDescription1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.AbilityDescription component46() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.AbilityDescription getAbilityDescription2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.AbilityDescription component47() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.AbilityDescription getAbilityDescription3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.AbilityDescription component48() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.AbilityDescription getAbilityDescription4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.AbilityDescription component49() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.AbilityDescription getAbilityDescription5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.AbilityDescription component50() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.matrix.data.network.model.AbilityDescription getBasicAttack() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component51() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGodAbility1URL() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "godAbility1_URL")
    @java.lang.Deprecated
    public static void getGodAbility1URL$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component52() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGodAbility2URL() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "godAbility2_URL")
    @java.lang.Deprecated
    public static void getGodAbility2URL$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component53() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGodAbility3URL() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "godAbility3_URL")
    @java.lang.Deprecated
    public static void getGodAbility3URL$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component54() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGodAbility4URL() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "godAbility4_URL")
    @java.lang.Deprecated
    public static void getGodAbility4URL$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component55() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGodAbility5URL() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "godAbility5_URL")
    @java.lang.Deprecated
    public static void getGodAbility5URL$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component56() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGodCardURL() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "godCard_URL")
    @java.lang.Deprecated
    public static void getGodCardURL$annotations() {
    }
    
    public final void setGodCardURL(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component57() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGodIconURL() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "godIcon_URL")
    @java.lang.Deprecated
    public static void getGodIconURL$annotations() {
    }
    
    public final int component58() {
        return 0;
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component59() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLatestGod() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component60() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getRetMsg() {
        return null;
    }
    
    @kotlinx.serialization.SerialName(value = "ret_msg")
    @java.lang.Deprecated
    public static void getRetMsg$annotations() {
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00c6\u0001\u00a8\u0006\u0006"}, d2 = {"Lcom/matrix/data/network/model/GodApiResult$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/matrix/data/network/model/GodApiResult;", "data_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final kotlinx.serialization.KSerializer<com.matrix.data.network.model.GodApiResult> serializer() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tH\u00d6\u0001\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u00d6\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VX\u00d6\u0005\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"com/matrix/data/network/model/GodApiResult.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/matrix/data/network/model/GodApiResult;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "data_debug"})
    @java.lang.Deprecated
    public static final class $serializer implements kotlinx.serialization.internal.GeneratedSerializer<com.matrix.data.network.model.GodApiResult> {
        @org.jetbrains.annotations.NotNull
        public static final com.matrix.data.network.model.GodApiResult.$serializer INSTANCE = null;
        
        private $serializer() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public kotlinx.serialization.KSerializer<?>[] childSerializers() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public com.matrix.data.network.model.GodApiResult deserialize(@org.jetbrains.annotations.NotNull
        kotlinx.serialization.encoding.Decoder decoder) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public kotlinx.serialization.descriptors.SerialDescriptor getDescriptor() {
            return null;
        }
        
        @java.lang.Override
        public void serialize(@org.jetbrains.annotations.NotNull
        kotlinx.serialization.encoding.Encoder encoder, @org.jetbrains.annotations.NotNull
        com.matrix.data.network.model.GodApiResult value) {
        }
        
        @org.jetbrains.annotations.NotNull
        public kotlinx.serialization.KSerializer<?>[] typeParametersSerializers() {
            return null;
        }
    }
}