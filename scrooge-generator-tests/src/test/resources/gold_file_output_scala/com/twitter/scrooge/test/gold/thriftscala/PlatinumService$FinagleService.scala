/**
 * Generated by Scrooge
 *   version: ?
 *   rev: ?
 *   built at: ?
 */
package com.twitter.scrooge.test.gold.thriftscala

import com.twitter.finagle.{
  service => ctfs,
  Filter => finagle$Filter,
  Service => finagle$Service,
  thrift => _,
  _
}
import com.twitter.finagle.stats.{Counter, NullStatsReceiver, StatsReceiver}
import com.twitter.finagle.thrift.RichServerParam
import com.twitter.io.Buf
import com.twitter.scrooge._
import com.twitter.util.{Future, Return, Throw, Throwables}
import java.nio.ByteBuffer
import java.util.Arrays
import org.apache.thrift.protocol._
import org.apache.thrift.TApplicationException
import org.apache.thrift.transport.TMemoryInputTransport
import scala.collection.mutable.{
  ArrayBuffer => mutable$ArrayBuffer, HashMap => mutable$HashMap}
import scala.collection.{Map, Set}

import scala.language.higherKinds


@javax.annotation.Generated(value = Array("com.twitter.scrooge.Compiler"))
class PlatinumService$FinagleService(
  iface: PlatinumService[Future],
  serverParam: RichServerParam
) extends GoldService$FinagleService(iface, serverParam) {
  import PlatinumService._

  @deprecated("Use com.twitter.finagle.thrift.RichServerParam", "2017-08-16")
  def this(
    iface: PlatinumService[Future],
    protocolFactory: TProtocolFactory,
    stats: StatsReceiver = NullStatsReceiver,
    maxThriftBufferSize: Int = Thrift.param.maxThriftBufferSize,
    serviceName: String = "PlatinumService"
  ) = this(iface, RichServerParam(protocolFactory, serviceName, maxThriftBufferSize, stats))

  @deprecated("Use com.twitter.finagle.thrift.RichServerParam", "2017-08-16")
  def this(
    iface: PlatinumService[Future],
    protocolFactory: TProtocolFactory,
    stats: StatsReceiver,
    maxThriftBufferSize: Int
  ) = this(iface, protocolFactory, stats, maxThriftBufferSize, "PlatinumService")

  @deprecated("Use com.twitter.finagle.thrift.RichServerParam", "2017-08-16")
  def this(
    iface: PlatinumService[Future],
    protocolFactory: TProtocolFactory
  ) = this(iface, protocolFactory, NullStatsReceiver, Thrift.param.maxThriftBufferSize)

  override def serviceName: String = serverParam.serviceName
  private[this] def responseClassifier: ctfs.ResponseClassifier = serverParam.responseClassifier
  private[this] def stats: StatsReceiver = serverParam.serverStats

  addService("moreCoolThings", {
    val statsFilter: finagle$Filter[(TProtocol, Int), Array[Byte], (TProtocol, Int), RichResponse[MoreCoolThings.Args, MoreCoolThings.Result]] = perMethodStatsFilter(MoreCoolThings)

    val methodService = new finagle$Service[MoreCoolThings.Args, MoreCoolThings.SuccessType] {
      def apply(args: MoreCoolThings.Args): Future[MoreCoolThings.SuccessType] = {
        if (_root_.com.twitter.finagle.tracing.Trace.isActivelyTracing) {
          _root_.com.twitter.finagle.tracing.Trace.recordRpc("moreCoolThings")
        }
        iface.moreCoolThings(args.request)
      }
    }

    val protocolExnFilter = new SimpleFilter[(TProtocol, Int), RichResponse[MoreCoolThings.Args, MoreCoolThings.Result]] {
      def apply(
        request: (TProtocol, Int),
        service: finagle$Service[(TProtocol, Int), RichResponse[MoreCoolThings.Args, MoreCoolThings.Result]]
      ): Future[RichResponse[MoreCoolThings.Args, MoreCoolThings.Result]] = {
        val iprot = request._1
        val seqid = request._2
        val res = service(request)
        res.transform {
          case _root_.com.twitter.util.Throw(e: TProtocolException) =>
            iprot.readMessageEnd()
            Future.value(
              ProtocolException(
                null,
                exception("moreCoolThings", seqid, TApplicationException.PROTOCOL_ERROR, e.getMessage),
                new TApplicationException(TApplicationException.PROTOCOL_ERROR, e.getMessage)))
          case _ =>
            res
        }
      }
    }

    val serdeFilter = new finagle$Filter[(TProtocol, Int), RichResponse[MoreCoolThings.Args, MoreCoolThings.Result], MoreCoolThings.Args, MoreCoolThings.SuccessType] {
      def apply(
        request: (TProtocol, Int),
        service: finagle$Service[MoreCoolThings.Args, MoreCoolThings.SuccessType]
      ): Future[RichResponse[MoreCoolThings.Args, MoreCoolThings.Result]] = {
        val iprot = request._1
        val seqid = request._2
        val args = MoreCoolThings.Args.decode(iprot)
        iprot.readMessageEnd()
        val res = service(args)
        res.transform {
          case _root_.com.twitter.util.Return(value) =>
            val methodResult = MoreCoolThings.Result(success = Some(value))
            Future.value(
              SuccessfulResult(
                args,
                reply("moreCoolThings", seqid, methodResult),
                methodResult))
          case _root_.com.twitter.util.Throw(e: com.twitter.scrooge.test.gold.thriftscala.AnotherException) => {
            val methodResult = MoreCoolThings.Result(ax = Some(e))
            Future.value(
              ThriftExceptionResult(
                args,
                reply("moreCoolThings", seqid, methodResult),
                methodResult))
          }
          case _root_.com.twitter.util.Throw(e: com.twitter.scrooge.test.gold.thriftscala.OverCapacityException) => {
            val methodResult = MoreCoolThings.Result(oce = Some(e))
            Future.value(
              ThriftExceptionResult(
                args,
                reply("moreCoolThings", seqid, methodResult),
                methodResult))
          }
          case t @ _root_.com.twitter.util.Throw(_) =>
            Future.const(t.cast[RichResponse[MoreCoolThings.Args, MoreCoolThings.Result]])
        }
      }
    }

    statsFilter.andThen(protocolExnFilter).andThen(serdeFilter).andThen(methodService)
  })
}
