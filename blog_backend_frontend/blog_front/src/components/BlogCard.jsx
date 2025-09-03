import React, { useMemo, useState } from "react";
import { Link } from "react-router-dom";
import api from "../api/api";
import { apiBaseUrl } from "../constants/constants";

/**
 * BlogCard (Light Mode Only)
 * Props:
 *  - title: string
 *  - imageUrl?: string
 *  - content: string
 *  - date?: string | Date
 *  - to?: string      // route path for Link
 *  - maxChars?: number
 */
export default function BlogCard({
  title,
  imageUrl,
  content,
  date,
  to,
  maxChars = 180,
}) {
  const [expanded, setExpanded] = useState(false);

  const { displayDate, displayTime } = useMemo(() => {
    if (!date) return { displayDate: null, displayTime: null };
    const d = typeof date === "string" ? new Date(date) : date;
    if (Number.isNaN(d.getTime()))
      return { displayDate: null, displayTime: null };

    const displayDate = d.toLocaleDateString(undefined, {
      year: "numeric",
      month: "short",
      day: "2-digit",
    });
    const displayTime = d.toLocaleTimeString(undefined, {
      hour: "2-digit",
      minute: "2-digit",
    });
    return { displayDate, displayTime };
  }, [date]);

  const needsTruncate = content && content.length > maxChars;
  const shownContent =
    expanded || !needsTruncate
      ? content
      : content.slice(0, maxChars).trim() + "…";

  return (
    <article className="group overflow-hidden rounded-2xl border border-gray-200 shadow-sm transition hover:shadow-md bg-white">
      {imageUrl ? (
        to ? (
          <Link to={to} aria-label={title} className="block">
            <img
              src={apiBaseUrl+"/uploads/"+imageUrl }
              alt={title}
              className="h-56 w-full object-cover transition-transform duration-300 group-hover:scale-[1.02]"
              loading="lazy"
            />
          </Link>
        ) : (
          <img
            src={imageUrl}
            alt={title}
            className="h-56 w-full object-cover transition-transform duration-300 group-hover:scale-[1.02]"
            loading="lazy"
          />
        )
      ) : null}

      <div className="p-5 sm:p-6">
        <header className="mb-3 flex items-start justify-between gap-3">
          <h3 className="text-lg font-semibold leading-snug tracking-tight text-gray-900">
            {to ? (
              <Link to={to} className="hover:underline focus:underline">
                {title}
              </Link>
            ) : (
              title
            )}
          </h3>

          {(displayDate || displayTime) && (
            <time
              dateTime={typeof date === "string" ? date : date?.toISOString?.()}
              className="shrink-0 rounded-full bg-gray-100 px-3 py-1 text-xs font-medium text-gray-700"
              title={
                displayTime
                  ? `${displayDate} • ${displayTime}`
                  : displayDate || undefined
              }
            >
              {displayDate}
              {displayTime ? ` • ${displayTime}` : ""}
            </time>
          )}
        </header>

        <p className="text-sm leading-relaxed text-gray-700">{shownContent}</p>

        {needsTruncate && (
          <div className="mt-3">
            <Link
            to={to || "#"}
              type="button"
              // onClick={() => setExpanded((v) => !v)}
              className="rounded-xl border border-gray-300 px-3 py-1.5 text-sm font-medium text-gray-800 transition hover:bg-gray-50 active:scale-[0.99]"
              aria-expanded={expanded}
              aria-label={expanded ? "Show less" : "Read more"}
            >
              {expanded ? "Show less" : "Read more"}
            </Link>
          </div>
        )}
      </div>
    </article>
  );
}
